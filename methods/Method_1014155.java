protected void activate(){
  modelRepository.addModelRepositoryChangeListener(this);
  for (  String modelName : modelRepository.getAllModelNamesOfType("persist")) {
    addModel(modelName);
  }
}
