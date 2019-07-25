@Override public void success(ActionInvocation invocation){
  try {
    ConnectionInfo info=new ConnectionInfo((Integer)invocation.getInput("ConnectionID").getValue(),(Integer)invocation.getOutput("RcsID").getValue(),(Integer)invocation.getOutput("AVTransportID").getValue(),new ProtocolInfo(invocation.getOutput("ProtocolInfo").toString()),new ServiceReference(invocation.getOutput("PeerConnectionManager").toString()),(Integer)invocation.getOutput("PeerConnectionID").getValue(),ConnectionInfo.Direction.valueOf(invocation.getOutput("Direction").toString()),ConnectionInfo.Status.valueOf(invocation.getOutput("Status").toString()));
    received(invocation,info);
  }
 catch (  Exception ex) {
    invocation.setFailure(new ActionException(ErrorCode.ACTION_FAILED,"Can't parse ConnectionInfo response: " + ex,ex));
    failure(invocation,null);
  }
}
