public void intern(CstMethodHandle methodHandle){
  if (methodHandle == null) {
    throw new NullPointerException("methodHandle == null");
  }
  throwIfPrepared();
  MethodHandleItem result=methodHandles.get(methodHandle);
  if (result == null) {
    result=new MethodHandleItem(methodHandle);
    methodHandles.put(methodHandle,result);
  }
}
