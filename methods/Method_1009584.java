public void success(ActionInvocation invocation){
  MediaInfo mediaInfo=new MediaInfo(invocation.getOutputMap());
  received(invocation,mediaInfo);
}
