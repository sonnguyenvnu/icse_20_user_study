public void startup(){
  isStarted=true;
  if (entryPoint != null) {
    bindings.put(Protocol.ENTRY_POINT_OBJECT_ID,entryPoint);
  }
  bindings.put(Protocol.DEFAULT_JVM_OBJECT_ID,defaultJVMView);
}
