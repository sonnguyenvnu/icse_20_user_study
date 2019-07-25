public void register(AsyncEmailSenderCallback callback){
  for (  AsyncEmailSenderCallback function : callbackFunctions) {
    if (function.getClass().getSimpleName().equals(callback.getClass().getSimpleName())) {
      return;
    }
  }
  callbackFunctions.add(callback);
}
