@Override public void enhance(List<ModelClass> modelClasses) throws Exception {
  for (  ModelClass modelClass : modelClasses) {
    inner_enhance(modelClass);
  }
}
