private void copyModels(){
  URL resource=Thread.currentThread().getContextClassLoader().getResource(MODEL_LOCATION);
  String dest=_.locateTmp("models");
  this.modelDir=dest;
  try {
    _.copyResourcesRecursively(resource,new File(dest));
    _.msg("copied models to: " + modelDir);
  }
 catch (  Exception e) {
    _.die("Failed to copy models. Please check permissions of writing to: " + dest);
  }
  addPath(dest);
}
