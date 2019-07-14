private void createClassPrepareRequest(String name){
  ClassPrepareRequest classPrepareRequest=runtime.vm().eventRequestManager().createClassPrepareRequest();
  classPrepareRequest.addClassFilter(name);
  classPrepareRequest.enable();
}
