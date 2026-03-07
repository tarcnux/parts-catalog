import http from "../http-common";

class VehicleDataService {
  getAll() {
    return http.get("/vehicles");
  }

  get(id) {
    return http.get(`/vehicles/${id}`);
  }

  create(data) {
    return http.post("/vehicles", data);
  }

  update(id, data) {
    return http.put(`/vehicles/${id}`, data);
  }

  delete(id) {
    return http.delete(`/vehicles/${id}`);
  }

  deleteAll() {
    return http.delete(`/vehicles`);
  }

  findByName(name) {
    return http.get(`/vehicles?name=${name}`);
  }

  findByPart(partId) {
    return http.get(`/parts/${partId}/vehicles`);
  }

  addToPart(partId, vehicleId) {
    return http.post(`/parts/${partId}/vehicles/${vehicleId}`);
  }

  removeFromPart(partId, vehicleId) {
    return http.delete(`/parts/${partId}/vehicles/${vehicleId}`);
  }
}

export default new VehicleDataService();
