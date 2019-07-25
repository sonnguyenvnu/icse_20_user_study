public void success(ActionInvocation invocation){
  boolean ok=true;
  long id=0;
  try {
    id=Long.valueOf(invocation.getOutput("Id").getValue().toString());
  }
 catch (  Exception ex) {
    invocation.setFailure(new ActionException(ErrorCode.ACTION_FAILED,"Can't parse GetSystemUpdateID response: " + ex,ex));
    failure(invocation,null);
    ok=false;
  }
  if (ok)   received(invocation,id);
}
