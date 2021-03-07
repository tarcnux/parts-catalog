<template>
  <div v-if="currentPart" class="edit-form">
    <h4>Atualizar peça</h4>
    <form>
      
      <div class="form-group">
        <label for="name">Nome</label>
        <input maxlength="200" type="text" class="form-control" id="name" required
          v-model="currentPart.name"/>
      </div>

      <div class="form-group">
        <label for="applicationVehicle">Veículo de aplicação</label>
        <input maxlength="200" type="text" class="form-control" id="applicationVehicle"
          v-model="currentPart.applicationVehicle"/>
      </div>

      <div class="form-group">
        <label for="netWeight">Peso líquido</label>
        <input type="number" placeholder="0.00" step="0.01"
            class="form-control" id="netWeight" required
          v-model="currentPart.netWeight"/>
      </div>

      <div class="form-group">
        <label for="grossWeight">Peso bruto</label>
        <input type="number" placeholder="0.00" step="0.01"
            class="form-control" id="grossWeight" required
          v-model="currentPart.grossWeight"/>
      </div>
    </form>   

    <button class="badge badge-danger mr-2"
      @click="deletePart"
    >
      Apagar
    </button>

    <button type="submit" class="badge badge-success"
      @click="updatePart"
    >
      Atualizar
    </button>

    <p>{{ message }}</p>

    <p v-if="errors.length">
    <strong class="text-danger">Por favor, corrija o(s) seguinte(s) erro(s):</strong><br>    
    <ul>
      <li v-for="(error, index) in errors" :key="index">{{ error }}</li>      
    </ul>
  </p>
  </div>

  <div v-else>
    <br />
    <p>Clique em uma peça por gentileza...</p>
  </div>
</template>

<script>
import PartDataService from "../services/PartDataService";

export default {
  name: "part",
  data() {
    return {
      currentPart: null,
      message: '',
      errors: []
    };
  },
  methods: {
    getPart(id) {
      PartDataService.get(id)
        .then(response => {
          this.currentPart = response.data;
          this.currentPart.netWeight = parseFloat(response.data.netWeight).toFixed(2)
          this.currentPart.grossWeight = parseFloat(response.data.grossWeight).toFixed(2)
          console.log(response.data);
          
        })
        .catch(e => {
          console.error(e);
        });
    },

    updatePart() {

      this.errors = [];

      if(parseFloat(this.currentPart.netWeight) > parseFloat(this.currentPart.grossWeight)) {
          this.errors.push('O peso líquido não pode ser maior que o peso bruto.');
        }
      
      if (!this.currentPart.name) {
        this.errors.push('Não é possível cadastrar peça sem nome.');
        }

      if (!this.currentPart.netWeight) {
        this.errors.push('Não é possível cadastrar peça sem peso líquido.');
      }

      if (!this.currentPart.grossWeight) {
        this.errors.push('Não é possível cadastrar peça sem peso bruto.');
      }

      if (this.errors.length === 0) {
        PartDataService.update(this.currentPart.id, this.currentPart)
        .then(response => {
          console.log(response.data);
          this.message = 'Peça atualizada com sucesso!';
          this.$router.push({ name: "parts" });
        })
        .catch(e => {
          console.error(e);
        });

      } else {
        this.message = 'Peça não atualizada!';
        console.error(this.errors);
      }
    },

    deletePart() {
      PartDataService.delete(this.currentPart.id)
        .then(response => {
          console.log(response.data);
          this.$router.push({ name: "parts" });
        })
        .catch(e => {
          console.error(e);
        });
    }
  },
  mounted() {
    this.message = '';
    this.getPart(this.$route.params.id);
  }
};
</script>

<style>
.edit-form {
  max-width: 640px;
  margin: auto;  
}
</style>
