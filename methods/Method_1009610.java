public void success(ActionInvocation invocation){
  boolean ok=true;
  int currentVolume=0;
  try {
    currentVolume=Integer.valueOf(invocation.getOutput("CurrentVolume").getValue().toString());
  }
 catch (  Exception ex) {
    invocation.setFailure(new ActionException(ErrorCode.ACTION_FAILED,"Can't parse ProtocolInfo response: " + ex,ex));
    failure(invocation,null);
    ok=false;
  }
  if (ok)   received(invocation,currentVolume);
}
