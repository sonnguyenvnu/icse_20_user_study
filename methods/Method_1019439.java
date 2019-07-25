@Override public SocksMethod select(MethodSelectionMessage message){
  int[] methods=message.getMethods();
  for (int i=0; i < methods.length; i++) {
    for (    SocksMethod method : supportMethods) {
      if (method.getByte() == methods[i]) {
        return method;
      }
    }
  }
  return new NoAcceptableMethod();
}
