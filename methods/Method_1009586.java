public void success(ActionInvocation invocation){
  TransportInfo transportInfo=new TransportInfo(invocation.getOutputMap());
  received(invocation,transportInfo);
}
