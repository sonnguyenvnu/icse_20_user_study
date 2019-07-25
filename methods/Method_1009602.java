@Override public void success(ActionInvocation invocation){
  try {
    Connection.Status status=Connection.Status.valueOf(invocation.getOutput("NewConnectionStatus").getValue().toString());
    Connection.Error lastError=Connection.Error.valueOf(invocation.getOutput("NewLastConnectionError").getValue().toString());
    success(new Connection.StatusInfo(status,(UnsignedIntegerFourBytes)invocation.getOutput("NewUptime").getValue(),lastError));
  }
 catch (  Exception ex) {
    invocation.setFailure(new ActionException(ErrorCode.ARGUMENT_VALUE_INVALID,"Invalid status or last error string: " + ex,ex));
    failure(invocation,null);
  }
}
