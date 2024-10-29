class Countries{
  dom;
  modal;
  
  state;  // state variables: entities, entity, mode (Add|Edit)

  constructor(){
    this.state = {'entities': new Array(), 'entity': this.emptyEntity(), 'mode':'A'};
    this.dom=this.render();
    this.modal = new bootstrap.Modal(this.dom.querySelector('#modal'));
    this.dom.querySelector("#countries #create").addEventListener('click',this.makenew);        
    this.dom.querySelector("#countries #search").addEventListener('click',this.search);
    this.dom.querySelector('#countries #modal #form #apply').addEventListener('click',this.add);
  }
  
  render=()=>{
    const html= `
            ${this.renderList()}
            ${this.renderModal()}    
        `;
       var rootContent= document.createElement('div');
       rootContent.id='countries';       
       rootContent.innerHTML=html;
       return rootContent;
  }

   renderList=()=>{
     return `
        <div id="list" class="container">     
            <div class="card bg-light">
                <h4 class="card-title mt-3 text-center">Polizas</h4>    
                <div class="card-body mx-auto w-75" >
                    <form id="form">
                        <div class="input-group mb-3">
                            <span class="input-group-text">Placa</span>
                            <input id="placa" type="text" class="form-control">
                          <div class="btn-toolbar">
                            <div class="btn-group me-2"><button type="button" class="btn btn-primary" id="search">Search</button> </div>
                            <div class="btn-group me-2"><button type="button" class="btn btn-primary" id="create">Create</button> </div>                        
                          </div>  
                        </div>
                    </form>

                    <div class="table-responsive " style="max-height: 300px; overflow: auto">
                        <table class="table" >
                            <thead class="table-dark">
                                <th scope="col">Placa</th>
                                <th scope="col">Fecha</th>
                                <th scope="col">Auto</th>
                                <th scope="col">Imagen</th>
                                <th scope="col">Valor</th>
                                <th scope="col">Ver</th>
                            </thead>
                            <tbody id="listbody">
                            
                            </tbody>
                       </table>
                    </div>                 
                </div>
            </div>
        </div>
        `;
   }
   
   renderModal=()=>{
     return `
        <div id="modal" class="modal fade" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header" >
                        <span style='margin-left:4em;font-weight: bold;'>Poliza</span> 
                       <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form id="form" >
                    <div class="modal-body">
                        <div class="input-group mb-3">
                            <span class="input-group-text">Placa</span>
                            <input id="placa" type="text" class="form-control">  
                        </div>
        
                        <div class="input-group mb-3">
                            <span class="input-group-text">Marca-Modelo</span>
                            <select class="form-select" name="marcaModelo" id="marcaModelo">
                                
                            </select>  
                        </div>
        
                        <div class="input-group mb-3">
                            <span class="input-group-text">Anio</span>
                            <input id="anio" type="text" class="form-control">  
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text">Valor</span>
                            <input id="valor" type="text" class="form-control">  
                        </div>
        
                        <div class="input-group mb-3">
                            <span class="input-group-text">Tipo</span>
                            <div class="input-group-text">
                                <input class="form-check-input mt-0" type="radio" id="tri" name="modoR" value="Trimestral" checked>
                                <label for="tri"> Trimestral </label>
                            </div>
                            <div class="input-group-text">
                                <input class="form-check-input mt-0" type="radio" id="sem" name="modoR" value="Semestral">
                                <label for="sem"> Semestral </label>
                            </div>
                            <div class="input-group-text">
                                <input class="form-check-input mt-0" type="radio" id="an" name="modoR" value="Anual">
                                <label for="an"> Anual </label>
                            </div>
                        </div>
        
                        <div class="input-group mb-3">
                            <span class="input-group-text">Coberturas</span>
                            <div id="coberturas" class="card-body mx-auto" >
                                    
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button id="apply" type="button" class="btn btn-primary" >Aplicar</button>
                    </div>
                    </form>                 
                </div>         
            </div>          
        </div>      
        `;     
    }

    showModal= async ()=>{
        // Load entity data into modal form
        this.modal.show();
  }
  
    load=()=>{
        this.state.entity.anio = this.dom.querySelector('#countries #modal #form #anio').value;
    
        this.state.entity.cliente={};
        this.state.entity.cliente.cedula = globalstate.user.id;

        var select = this.dom.querySelector('#countries #modal #form #marcaModelo');
        this.state.entity.modelo={};
        this.state.entity.modelo.id = select.options[select.selectedIndex].value;

        this.state.entity.num_Placa = this.dom.querySelector('#countries #modal #form #placa').value;

        this.state.entity.plazos_pago = this.dom.querySelector('input[name="modoR"]:checked').value;

        this.state.entity.valor_asegurado = parseInt(this.dom.querySelector('#countries #modal #form #valor').value, 10);
        
        var inputs = new Array();
        inputs = this.dom.querySelectorAll('input[name="coberturas"]:checked');
        
        var coberturas = [];
        inputs.forEach(c=>coberturas.push({id:c.value}));
        
        this.state.entity.coberturas = coberturas;
    }
    
    reset=()=>{
        this.state.entity=this.emptyEntity();
    }
    
    emptyEntity=()=>{
        return {};
    }


  add=()=>{ 
    this.load();
    
    fetch(`${backend}/polizas`, {
    method: 'POST',
    headers: {
    'Content-Type': 'application/json'
    },
    body: JSON.stringify(this.state.entity) // Conversion in JSON format
    })
    .catch(function (error) {
    console.log('Failed to store data on server: ', error);
    });
      // Validate data, load into entity, invoque backend for adding
    this.list();
    this.reset();
    this.modal.hide();
  } 
  
  update=()=>{
    // Validate data, load into entity, invoque backend for updating    
    this.list();
    this.reset();
    this.modal.hide();
  }
  
   validate=()=>{
       // validate data
  }

  list=()=>{
    const request = new Request(`${backend}/polizas`, {method: 'GET', headers: { }});
    (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {errorMessage(response.status);return;}
        var countries = await response.json();
        this.state.entities = countries;
        var listing=this.dom.querySelector("#countries #list #listbody");
        listing.innerHTML="";
        this.state.entities.forEach( e=>this.row(listing,e));         
    })();       
  }  

  row=(list,c)=>{
	var tr =document.createElement("tr");
	tr.innerHTML=`
                <td>${c.num_Placa}</td>
                <td>${c.fecha}</td>
                <td>${c.modelo.descripcion} - ${c.anio}</td>
                <td> </td>
                <td>${c.valor_asegurado}</td>
                <td><a id="ver" href="#"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eye" viewBox="0 0 16 16">
                                            <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z"/>
                                            <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z"/>
                                         </svg>
                    </a>
                </td>`;  
                var edit = tr.querySelector("#ver");
                edit.addEventListener("click",e=>this.showPoliza(c.id));
	list.append(tr);           
  }
  
  makenew=()=>{
      this.reset();
      this.state.mode='A'; //adding
      this.showModal();
      this.getMarcasModelos();
      this.getCategoriasCoberturas();
  }
    
  search= async ()=>{
      var placa=document.querySelector('#countries #list #form #placa').value;
      let request = new Request(`${backend}/polizas?placa=${placa}`,{method: 'GET', headers:{ }});
      let response = await fetch(request);
      if(!response.ok){
          errorMessage(response.status);
          return;
      }
      this.state.entities = await response.json();
      var listing=this.dom.querySelector("#countries #list #listbody");
      listing.innerHTML="";
      this.state.entities.forEach( e=>this.row(listing,e));
  }
  
  showPoliza=(id)=>{
      app.polizaShow(id);
  }
  
  getMarcasModelos=()=>{
    const request = new Request(`${backend}/marcas`, {method: 'GET', headers: { }});
    (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status);
            return;
        }
        var marcas = await response.json();
        
        const requestModelos = new Request(`${backend}/modelos`, {method: 'GET', headers: { }});
        const responseModelos = await fetch(requestModelos);
        if (!responseModelos.ok) {errorMessage(responseModelos.status);return;}
        var modelos = await responseModelos.json();
        
        var listing=this.dom.querySelector("#countries #modal #form #marcaModelo");
        listing.innerHTML="";
        marcas.forEach( marca=>this.renderMarcas(listing,marca,modelos));         
    })();       
  }
  
  renderMarcas=(select,marca,modelos)=>{
        var optgroup = document.createElement("optgroup");
        optgroup.setAttribute("label", marca.descripcion);
        
        modelos.forEach(modelo=>this.renderModelos(optgroup,marca,modelo));
        
        select.append(optgroup);
  }
  
  renderModelos=(optgroup,marca,modelo)=>{
        if (modelo.marca.id === marca.id) {
            var option = document.createElement("option");
            option.setAttribute("value", modelo.id);
            option.append(document.createTextNode(modelo.descripcion));

            optgroup.append(option);
        }
  }
  
  getCategoriasCoberturas=()=>{
    const request = new Request(`${backend}/categorias`, {method: 'GET', headers: { }});
    (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status);
            return;
        }
        var categorias = await response.json();
        
        const requestCoberturas = new Request(`${backend}/coberturas`, {method: 'GET', headers: { }});
        const responseCoberturas = await fetch(requestCoberturas);
        if (!responseCoberturas.ok) {errorMessage(responseCoberturas.status);return;}
        var coberturas = await responseCoberturas.json();
        
        var listing=this.dom.querySelector("#countries #modal #form #coberturas");
        listing.innerHTML="";
        categorias.forEach( categoria=>this.renderCategorias(listing,categoria,coberturas));         
    })();       
  }
  
  renderCategorias=(div,categoria,coberturas)=>{
        
      
        var label = document.createElement("label");
        label.append(document.createTextNode("Categoria: "+categoria.descripcion));
        
        div.append(label);
        
        var br2 = document.createElement("br");
        div.append(br2);
        
        coberturas.forEach(cobertura=>this.renderCoberturas(div,categoria,cobertura));
        
        var br1 = document.createElement("br");
        div.append(br1);
  }
  
  renderCoberturas=(div,categoria,cobertura)=>{
        if (cobertura.categoria.id === categoria.id) {
            var input = document.createElement("input");
            input.setAttribute("type", "checkbox");
            input.setAttribute("name", "coberturas");
            input.setAttribute("value", cobertura.id);

            div.append(input);
            
            div.append(document.createTextNode(cobertura.descripcion));
            
            var br = document.createElement("br");
            div.append(br);
        }
  }
} 
