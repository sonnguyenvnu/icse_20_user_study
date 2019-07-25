@Override public void dispose(){
  ArrayList<DescriptorModel> models=new ArrayList<>(myModels.values());
  myModels.clear();
  for (  DescriptorModel m : models) {
    SModule module=m.getModule();
    ((SModuleBase)module).unregisterModel(m);
  }
}
