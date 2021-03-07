<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-light bg-warning">
      <router-link to="/" class="navbar-brand">tarcnux</router-link>
      <div class="navbar-nav mr-auto">
        <li class="nav-item">
          <router-link to="/parts" class="nav-link">Listar Peças</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/add" class="nav-link">Cadastrar nova peça</router-link>
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