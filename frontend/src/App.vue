<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-light" style="background-color:#e77924">
      <router-link to="/" class="navbar-brand">Catálogo</router-link>
      <div class="navbar-nav mr-auto">
        <li class="nav-item">
          <a href="/parts" class="nav-link" style="font-size:1.3em">Listar Peças</a>
        </li>
        <li class="nav-item">
          <router-link to="/add" class="nav-link" style="font-size:1.3em">Cadastrar peça</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/vehicles" class="nav-link" style="font-size:1.3em">Listar veículos</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/vehicles/add" class="nav-link" style="font-size:1.3em">Cadastrar veículo</router-link>
        </li>
      </div>
    </nav>

    <div class="container mt-3">
      <router-view />
    </div>
  </div>
</template>

<script>
export default {
  name: "app"
};
</script>
