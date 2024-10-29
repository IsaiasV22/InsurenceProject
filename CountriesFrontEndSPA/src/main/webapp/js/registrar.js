class Registrar{
  dom;
  modal;
  
  state;  // state variables: entities, entity, mode (Add|Edit)

  constructor(){
    this.state = {'entities': new Array(), 'entity': this.emptyEntity(), 'mode':'A'};
    this.dom=this.render();
    //this.modal = new bootstrap.Modal(this.dom.querySelector('#modal'));
    this.dom.querySelector("#registrar #apply").addEventListener('click',this.add);        
    this.dom.querySelector("#registrar #cancel").addEventListener('click',this.cerrar);
  }
  
  render=()=>{
    const html= `
            ${this.renderList()}    
        `;
       var rootContent= document.createElement('div');
       rootContent.id='registrar';       
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
                                <input type="text" class="form-control" name="cedula" id="cedula">
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
        this.state.entity=Object.fromEntries((new FormData(this.dom.querySelector('#registrar #list #form'))).entries());
    }
    
    reset=()=>{
        this.state.entity=this.emptyEntity();
    }
    
    emptyEntity=()=>{
        return null;
    }


    add=()=>{ 
      this.load();

      fetch(`${backend}/clientes`, {
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
      this.reset();
      this.cerrar();
    } 

     validate=()=>{
         // validate data
    }

    cerrar=()=>{
        app.renderBodyFiller();
    }
}