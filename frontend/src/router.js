import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/",
      alias: "/parts",
      name: "parts",
      component: () => import("./components/PartsList")
    },
    {
      path: "/parts/:id",
      name: "part-details",
      component: () => import("./components/Part")
    },
    {
      path: "/add",
      name: "add",
      component: () => import("./components/AddPart")
    }
  ]
});