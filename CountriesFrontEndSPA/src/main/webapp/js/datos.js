class Datos{
  dom;
  
  state;  // state variables: entities, entity, mode (Add|Edit)

  constructor(){
    this.state = {'entities': new Array(), 'entity': this.emptyEntity(), 'mode':'A'};
    this.dom=this.render();
    this.dom.querySelector("#datos #apply").addEventListener('click',this.add);        
    this.dom.querySelector("#datos #cancel").addEventListener('click',this.cerrar);
  }
  
  render=()=>{
    const html= `
            ${this.renderList()}    
        `;
       var rootContent= document.createElement('div');
       rootContent.id='datos';       
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
                                <span class="input-group-text">Cedula</span>
                                <input type="text" class="form-control" name="cedula" id="cedula" disabled>
                            </div>

                            <div class="input-group mb-3">
                                <span class="input-group-text">Clave</span>
                                <input type="password" class="form-control" name="clave" id="clave">
                            </div>

                            <div class="input-group mb-3">
                                <span class="input-group-text">Nombre</span>
                                <input type="text" class="form-control" name="nombre" id="nombre">
                            </div>
        
                            <div class="input-group mb-3">
                                <span class="input-group-text">Telefono</span>
                                <input type="text" class="form-control" name="telefono" id="telefono">
                            </div>

                            <div class="input-group mb-3">
                                <span class="input-group-text">Correo</span>
                                <input type="text" class="form-control" name="correo" id="correo">
                            </div>

                            <div class="input-group mb-3">
                                <span class="input-group-text">Tarjeta</span>
                                <input type="text" class="form-control" name="tarjeta" id="tarjeta">
                            </div>

                            <button id="apply" type="button" class="btn btn-primary">Confirmar</button>
                            <button id="cancel" type="button" class="btn btn-danger">Cancelar</button>
                        </form>
                    </div>
                </div>
            </div>
        `;
   }
  
    load=()=>{
        this.state.entity=Object.fromEntries((new FormData(this.dom.querySelector('#datos #list #form'))).entries());
        this.state.entity.cedula = this.dom.querySelector('#datos #list #form #cedula').value;
    }
    
    reset=()=>{
        this.state.entity=this.emptyEntity();
    }
    
    emptyEntity=()=>{
        // return an empty entity
    }


    add=()=>{ 
      this.load();

      fetch(`${backend}/clientes`, {
      method: 'PUT',
      headers: {
      'Content-Type': 'application/json'
      },
      body: JSON.stringify(this.state.entity) // Conversion in JSON format
      })
      .catch(function (error) {
      console.log('Failed to store data on server: ', error);
      });
        // Validate data, load into entity, invoque backend for adding
      this.reset();
      this.cerrar();
    } 

    validate=()=>{
         // validate data
    }

    cerrar=()=>{
        app.countriesShow();
    }
    
    getCliente=()=>{
        var id = globalstate.user.id;
        const request = new Request(`${backend}/clientes/${id}`, {method: 'GET', headers: { }});
        (async ()=>{
            const response = await fetch(request);
            if (!response.ok) {errorMessage(response.status);return;}
            var cliente = await response.json();
            this.getDatos(cliente);
        })();
    }
    
    getDatos=(c)=>{
        this.dom.querySelector("#datos #list #form #cedula").setAttribute("value", c.cedula);
        this.dom.querySelector("#datos #list #form #clave").setAttribute("value", c.clave);
        this.dom.querySelector("#datos #list #form #nombre").setAttribute("value", c.nombre);
        this.dom.querySelector("#datos #list #form #telefono").setAttribute("value", c.telefono);
        this.dom.querySelector("#datos #list #form #correo").setAttribute("value", c.correo);
        this.dom.querySelector("#datos #list #form #tarjeta").setAttribute("value", c.tarjeta);
    }
}