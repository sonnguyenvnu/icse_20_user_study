protected void deactivate(){
  modelRepository.removeModelRepositoryChangeListener(this);
  for (  String modelName : modelRepository.getAllModelNamesOfType("persist")) {
    removeModel(modelName);
  }
}
