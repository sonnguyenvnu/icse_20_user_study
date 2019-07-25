@Override public void enhance(List<ModelClass> modelClasses) throws Exception {
  for (  ModelClass modelClass : modelClasses) {
    copyStaticFieldsToSubclass(modelClass.originClass);
    copyStaticMethodsToSubclass(modelClass.originClass);
    enhanceModelMethods(modelClass.originClass);
  }
}
