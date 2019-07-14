private void converterParamsClass(final Class[] args,final Object[] arguments){
  if (arguments == null || arguments.length < 1) {
    return;
  }
  for (int i=0; i < arguments.length; i++) {
    if (arguments[i] != null) {
      args[i]=arguments[i].getClass();
    }
  }
}
