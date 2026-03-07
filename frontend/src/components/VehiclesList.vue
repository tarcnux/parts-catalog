<template>
  <div class="list row">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Buscar veículo por nome" v-model="name" />
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button" @click="searchByName">Buscar</button>
        </div>
      </div>
    </div>

    <div class="col-md-6">
      <h4>Listagem de veículos</h4>
      <ul class="list-group">
        <li
          class="list-group-item"
          :class="{ active: index == currentIndex }"
          v-for="(vehicle, index) in vehicles"
          :key="index"
          @click="setActiveVehicle(vehicle, index)"
        >
          {{ vehicle.name }}

          <button class="badge badge-danger mr-2 float-right" @click.stop="deleteVehicle(vehicle, index)">Apagar</button>
        </li>
      </ul>

      <button class="m-3 btn btn-sm btn-danger" @click="removeAllVehicles">Apagar todos os veículos</button>
    </div>

    <div class="col-md-6">
      <div v-if="currentVehicle">
        <h4>Veículo</h4>
        <div><label><strong>ID:</strong></label> {{ currentVehicle.id }}</div>
        <div><label><strong>Nome:</strong></label> {{ currentVehicle.name }}</div>
      </div>
      <div v-else>
        <br />
        <p>Clique em um veículo por gentileza...</p>
      </div>
    </div>
  </div>
</template>

<script>
import VehicleDataService from "../services/VehicleDataService";

export default {
  name: "vehicles-list",
  data() {
    return {
      vehicles: [],
      currentVehicle: null,
      currentIndex: -1,
      name: ""
    };
  },
  methods: {
    retrieveVehicles() {
      VehicleDataService.getAll()
        .then(response => {
          this.vehicles = response.data;
        })
        .catch(e => {
          console.error(e);
        });
    },

    refreshList() {
      this.retrieveVehicles();
      this.currentVehicle = null;
      this.currentIndex = -1;
    },

    setActiveVehicle(vehicle, index) {
      this.currentVehicle = vehicle;
      this.currentIndex = index;
    },

    deleteVehicle(vehicle, index) {
      VehicleDataService.delete(vehicle.id)
        .then(() => {
          this.vehicles.splice(index, 1);
          if (this.currentIndex === index) {
            this.currentVehicle = null;
            this.currentIndex = -1;
          }
        })
        .catch(e => {
          console.error(e);
        });
    },

    removeAllVehicles() {
      VehicleDataService.deleteAll()
        .then(() => {
          this.refreshList();
        })
        .catch(e => {
          console.error(e);
        });
    },

    searchByName() {
      VehicleDataService.findByName(this.name)
        .then(response => {
          this.vehicles = response.data;
        })
        .catch(e => {
          console.error(e);
        });
    }
  },
  mounted() {
    this.retrieveVehicles();
  }
};
</script>

<style>
.list {
  text-align: left;
  max-width: 1024px;
  margin: auto;
}

.active {
  background-color: #e77924 !important;
}
</style>
