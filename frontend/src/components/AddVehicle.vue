<template>
  <div class="submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="name">Nome do veículo</label>
        <input type="text" class="form-control" id="name" required v-model="vehicle.name" />
      </div>

      <button @click="saveVehicle" class="btn btn-success">Cadastrar veículo</button>
    </div>

    <div v-else>
      <h4>Veículo cadastrado com sucesso!</h4>
      <button class="btn btn-success" @click="newVehicle">Novo veículo</button>
    </div>
  </div>
</template>

<script>
import VehicleDataService from "../services/VehicleDataService";

export default {
  name: "add-vehicle",
  data() {
    return {
      vehicle: {
        id: null,
        name: ""
      },
      submitted: false
    };
  },
  methods: {
    saveVehicle() {
      const data = {
        name: this.vehicle.name
      };

      VehicleDataService.create(data)
        .then(response => {
          this.vehicle.id = response.data.id;
          this.submitted = true;
        })
        .catch(e => {
          console.error(e);
        });
    },

    newVehicle() {
      this.submitted = false;
      this.vehicle = {
        id: null,
        name: ""
      };
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
