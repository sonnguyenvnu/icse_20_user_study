protected void reportException(String message,ObjectReference or,ThreadReference thread){
  listener.statusError(findException(message,or,thread));
}
