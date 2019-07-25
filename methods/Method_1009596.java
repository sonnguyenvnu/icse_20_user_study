@Override public void success(ActionInvocation invocation){
  try {
    ActionArgumentValue sink=invocation.getOutput("Sink");
    ActionArgumentValue source=invocation.getOutput("Source");
    received(invocation,sink != null ? new ProtocolInfos(sink.toString()) : null,source != null ? new ProtocolInfos(source.toString()) : null);
  }
 catch (  Exception ex) {
    invocation.setFailure(new ActionException(ErrorCode.ACTION_FAILED,"Can't parse ProtocolInfo response: " + ex,ex));
    failure(invocation,null);
  }
}
