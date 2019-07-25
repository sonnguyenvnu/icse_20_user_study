public void upload(BinaryData data){
  reset();
  target.path(ResourcesServiceName.upload.name()).request().accept(MediaType.TEXT_PLAIN).post(Entity.entity(data,MediaType.APPLICATION_JSON));
}
