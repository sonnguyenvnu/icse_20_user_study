public void attach(){
  if (myModule == null) {
    throw new IllegalStateException("Module is null");
  }
  myRepository=myModule.getRepository();
  myModule.addModuleListener(myModuleListener);
  update();
}
