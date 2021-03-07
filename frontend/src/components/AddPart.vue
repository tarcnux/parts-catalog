<template>
  <div class="submit-form">

    <p v-if="errors.length">
    <strong class="text-danger">Peça não cadastrada</strong><br>
    <b>Por favor, corrija o(s) seguinte(s) erro(s):</b>
    <ul>
      <li v-for="(error, index) in errors" :key="index">{{ error }}</li>      
    </ul>
  </p>



    <div v-if="!submitted">

    <h4>Adicionar uma nova peça</h4>

    <div class="form-group">
      <label for="name">Nome</label>
      <input
        maxlength="200"
        type="text"
        class="form-control"
        id="name"
        required
        v-model="part.name"
        name="name"
      />
    </div>

    <div class="form-group">
      <label for="applicationVehicle">Veículo de aplicação</label>
      <input
        maxlength="200"
        type="text"
        class="form-control"
        id="applicationVehicle"          
        v-model="part.applicationVehicle"
        name="applicationVehicle"
      /><!-- Campo não obrigatório -->
    </div>

    <div class="form-group">
      <label for="netWeight">Peso líquido</label>
      <input
        type="number"
        class="form-control"
        id="netWeight"
        required
        v-model="part.netWeight"
        name="netWeight"
      />
    </div>

    <div class="form-group">
      <label for="grossWeight">Peso Bruto</label>
      <input
        type="number"
        class="form-control"
        id="grossWeight"
        required
        v-model="part.grossWeight"
        name="grossWeight"
      />
    </div>

      <button @click="saveTutorial" class="btn btn-success">Cadastrar</button>
    </div>

    <div v-else>
      <h4>Peça cadastrada com sucesso!</h4>
      <button class="btn btn-success" @click="newPart">Nova peça</button>
    </div>

    
  </div>
</template>

<script>
import PartDataService from "../services/PartDataService";

export default {
  name: "add-part",
  data() {
    return {
      part: {
        id: null,
        name: "",
        applicationVehicle: "",
        netWeight: 0.0,
        grossWeight: 0.0
      },
      submitted: false,
      errors: []
    };
  },
  methods: {
    savePart() {
      var data = {
        name: this.part.name,
        applicationVehicle: this.part.applicationVehicle,
        netWeight: this.part.netWeight,
        grossWeight: this.part.grossWeight
      };

      this.errors = [];

      if (parseInt(data.netWeight) > parseInt(data.grossWeight)) {
        this.errors.push('O peso líquido não pode ser maior que o peso bruto.');
      }

      if (!data.name) {
        this.errors.push('Não é possível cadastrar peça sem nome.');
      }

      if (!data.netWeight) {
        this.errors.push('Não é possível cadastrar peça sem peso líquido.');
      }

      if (!data.grossWeight) {
        this.errors.push('Não é possível cadastrar peça sem peso bruto.');
      }

      
      if(this.errors.length === 0) {

      PartDataService.create(data)
        .then(response => {
          this.part.id = response.data.id;
          console.log(response.data);
          this.submitted = true;
        })
        .catch(e => {
          console.error(e);
        });
      } else {
        //Força mudança de estado para recarregar
        this.submitted = false;
        //Caso haja erro não apaga o formulário
        this.part = {
          name: data.name,
          applicationVehicle: data.applicationVehicle,
          netWeight: data.netWeight,
          grossWeight: data.grossWeight
        };
        console.error(this.errors);
      }
    },
    
    newTutorial() {
      this.submitted = false;
      this.tutorial = {};
    }
  }
};
</script>

<style>
.submit-form {
  max-width: 640px;
  margin: auto;
}
</style>