class Poliza{
  dom;
  
  total=0;
  valorAsegurado=0;
  
  state;  // state variables: entities, entity, mode (Add|Edit)

  constructor(){
    this.state = {'entities': new Array(), 'entity': this.emptyEntity(), 'mode':'A'};
    this.dom=this.render();
    //this.dom.querySelector("#poliza #apply").addEventListener('click',this.add);        
    this.dom.querySelector("#poliza #cancel").addEventListener('click',this.cerrar);
  }
  
  render=()=>{
    const html= `
            ${this.renderList()}    
        `;
       var rootContent= document.createElement('div');
       rootContent.id='poliza';       
       rootContent.innerHTML=html;
       return rootContent;
  }

   renderList=()=>{
     return `
            <div id="list" class="container">     
                <div class="card bg-light">
                    <div class="card-body mx-auto w-75" >
                        <form id="form" class="registro-container" >
                            <div class="input-group mb-3">
                                <span class="input-group-text">Placa</span>
                                <input type="text" class="form-control" name="placa" id="placa" disabled>
                            </div>

                            <div class="input-group mb-3">
                                <span class="input-group-text">Modelo</span>
                                <input type="text" class="form-control" name="modelo" id="modelo" disabled>
                            </div>

                            <div class="input-group mb-3">
                                <span class="input-group-text">Valor</span>
                                <input type="text" class="form-control" name="valor" id="valor" disabled>
                            </div>
        
                            <div class="input-group mb-3">
                                <span class="input-group-text">Plazo</span>
                                <input type="text" class="form-control" name="plazo" id="plazo" disabled>
                            </div>

                            <div class="input-group mb-3">
                                <span class="input-group-text">Fecha</span>
                                <input type="text" class="form-control" name="fecha" id="fecha" disabled>
                            </div>

                            <div class="input-group mb-3">
                                <span class="input-group-text">Coberturas</span>
                                <div id="coberturas" class="card-body mx-auto" >
                                    
                                </div>
                            </div>
        
                            <div class="input-group mb-3">
                                <span class="input-group-text">Total</span>
                                <input type="text" class="form-control" name="total" id="total" disabled>
                            </div>

                            <button id="cancel" type="button" class="btn btn-danger">Cerrar</button>
                        </form>
                    </div>
                </div>
            </div>
        `;
   }
  

    
    reset=()=>{
        this.state.entity=this.emptyEntity();
    }
    
    emptyEntity=()=>{
        // return an empty entity
    } 

    cerrar=()=>{
        app.countriesShow();
    }
    
    getPoliza=(id)=>{
        const request = new Request(`${backend}/polizas/${id}`, {method: 'GET', headers: { }});
        (async ()=>{
            const response = await fetch(request);
            if (!response.ok) {errorMessage(response.status);return;}
            var poliza = await response.json();
            this.getDatosPoliza(poliza);
            this.valorAsegurado=poliza.valor_asegurado;
        })();
    }
    
    getDatosPoliza=(p)=>{
        this.dom.querySelector("#poliza #list #form #placa").setAttribute("value", p.num_Placa);
        this.dom.querySelector("#poliza #list #form #modelo").setAttribute("value", p.modelo.descripcion);
        this.dom.querySelector("#poliza #list #form #valor").setAttribute("value", p.valor_asegurado);
        this.dom.querySelector("#poliza #list #form #plazo").setAttribute("value", p.plazos_pago);
        this.dom.querySelector("#poliza #list #form #fecha").setAttribute("value", p.fecha);
    }
    
    getCoberturas=(id)=>{
        const request = new Request(`${backend}/coberturas/${id}`, {method: 'GET', headers: { }});
        (async ()=>{
            const response = await fetch(request);
            if (!response.ok) {errorMessage(response.status);return;}
            var coberturas = await response.json();
            var listing=this.dom.querySelector("#poliza #list #form #coberturas");
            listing.innerHTML="";
            this.total=0;
            coberturas.forEach(c=>this.row(listing,c));
            this.dom.querySelector("#poliza #list #form #total").setAttribute("value", this.total);
        })();
    }
    
    row=(list,c)=>{
	var p = document.createElement("p");
	p.append(document.createTextNode(c.descripcion));  
	list.append(p);
        this.total += this.getPago(c);
    }
    
    getPago=(c)=>{
	var calculo = this.valorAsegurado * c.costo_porcentual / 100;
        if(calculo > c.costo_minimo)
            return calculo;
        else
            return c.costo_minimo;
    }
}