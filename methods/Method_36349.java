@Override public Map<String,Resource> getSpringResources(){
  if (springResources == null) {
    loadSpringXMLs();
  }
  return springResources;
}
