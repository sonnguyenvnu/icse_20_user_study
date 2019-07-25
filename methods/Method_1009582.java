public void success(ActionInvocation invocation){
  String actionsString=(String)invocation.getOutput("Actions").getValue();
  received(invocation,TransportAction.valueOfCommaSeparatedList(actionsString));
}
