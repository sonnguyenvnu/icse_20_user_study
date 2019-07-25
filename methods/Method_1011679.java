private void check(){
  setErrorText(NewModuleUtil.check(myProject.getRepository(),MPSExtentions.DOT_DEVKIT,getDevkitName(),getDevkitLocation()));
}
