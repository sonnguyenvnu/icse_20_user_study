@Override protected boolean allowMethod(String method){
  return allowAllMethods || super.allowMethod(method);
}
