public void apply(){
  myModuleDescriptor.getModelRootDescriptors().clear();
  myModuleDescriptor.getModelRootDescriptors().addAll(getDescriptors());
}
