public HttpHeaders create(){
  if (model.getName() == null || model.getBreed() == null) {
    return null;
  }
  Pet dbPet=model;
  dbPet.setId(UUID.randomUUID().toString());
  return new DefaultHttpHeaders("success").setLocationId(model.getId());
}
