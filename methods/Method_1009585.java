public void success(ActionInvocation invocation){
  PositionInfo positionInfo=new PositionInfo(invocation.getOutputMap());
  received(invocation,positionInfo);
}
