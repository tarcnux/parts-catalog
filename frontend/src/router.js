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
    },
    {
      path: "/vehicles",
      name: "vehicles",
      component: () => import("./components/VehiclesList")
    },
    {
      path: "/vehicles/add",
      name: "add-vehicle",
      component: () => import("./components/AddVehicle")
    }
  ]
});
