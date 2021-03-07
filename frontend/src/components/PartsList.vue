<template>
  <div class="list row">

    <div class="col-md-8">

      <div class="input-group mb-3">
        <input type="text" class="form-control"
            placeholder="Buscar por nome"
            v-model="name"/>
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button"
            @click="searchByName">
            Busca por nome
          </button>
        </div>
      </div>

      <div class="input-group mb-3 ml-2">
        <input type="text" class="form-control"
            placeholder="Buscar por veículo de aplicação"
            v-model="applicationVehicle"/>
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button"
            @click="searchByApplicationVehicle">
            Busca por veículo de aplicação
          </button>
        </div>
      </div>


    </div>
    <div class="col-md-4">
      <h4>Listagem de Peças</h4>
      <ul class="list-group">
        <li class="list-group-item"
          :class="{ active: index == currentIndex }"
          v-for="(part, index) in parts"
          :key="index"
          @click="setActivePart(part, index)"
        >
          {{ part.name }}

          <button class="badge badge-danger mr-2 float-right"
          @click="deletePart(part, index)"
          >
            Apagar
          </button>

        </li>
      </ul>

      <button class="m-3 btn btn-sm btn-danger" @click="removeAllParts">
        Apagar todas as peças
      </button>
    </div>
    <div class="col-md-6">
      <div v-if="currentPart">
        <h4>Peça</h4>
        <div>
          <label><strong>Nome:</strong></label> {{ currentPart.name }}
        </div>
        <div>
          <label><strong>Veículo:</strong></label> {{ currentPart.applicationVehicle }}
        </div>
        <div>
          <label><strong>Peso líquido:</strong></label> {{ currentPart.netWeight }}
        </div>
        <div>
          <label><strong>Peso Bruto:</strong></label> {{ currentPart.grossWeight }}
        </div>

        <a class="badge badge-warning"
          :href="'/parts/' + currentPart.id"
        >
          Alterar
        </a>
      </div>
      <div v-else>
        <br />
        <p>Clique em uma peça por gentileza...</p>
      </div>
    </div>
  </div>
</template>

<script>
import PartDataService from "../services/PartDataService";

export default {
  name: "parts-list",
  data() {
    return {
      parts: [],
      currentPart: null,
      currentIndex: -1,
      name: "",
      applicationVehicle: ""
    };
  },
  methods: {
    retrieveParts() {
      PartDataService.getAll()
        .then(response => {
          this.parts = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.error(e);
        });
    },

    refreshList() {
      this.retrieveParts();
      this.currentPart = null;
      this.currentIndex = -1;
    },

    setActivePart(part, index) {
      this.currentPart = part;
      this.currentIndex = index;
    },

    deletePart(part, index) {      
      PartDataService.delete(part.id)
        .then(response => {
          console.log(response.data);          
          this.parts.splice(index,1);
        })
        .catch(e => {
          console.error(e);
        });
    },

    removeAllParts() {
      PartDataService.deleteAll()
        .then(response => {
          console.log(response.data);
          this.refreshList();
        })
        .catch(e => {
          console.error(e);
        });
    },

    searchByName() {
      PartDataService.findByName(this.name)
        .then(response => {
          this.parts = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.error(e);
        });
    },
    
    searchByApplicationVehicle() {
      PartDataService.findByVehicle(this.applicationVehicle)
        .then(response => {
          this.parts = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.error(e);
        });
    }
  },
  mounted() {
    this.retrieveParts();
  }
};
</script>

<style>
.list {
  text-align: left;
  max-width: 1024px;
  margin: auto;
}
</style>