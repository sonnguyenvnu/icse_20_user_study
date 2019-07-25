public void detach(){
  myIsAttach=false;
  final ModelAccess ma=myRepo.getModelAccess();
  if (ma.canRead()) {
    run();
  }
 else {
    ma.runReadAction(this);
  }
}
