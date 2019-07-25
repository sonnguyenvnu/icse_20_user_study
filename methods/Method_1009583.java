public void success(ActionInvocation invocation){
  DeviceCapabilities caps=new DeviceCapabilities(invocation.getOutputMap());
  received(invocation,caps);
}
