@Override protected void sendAppError(HttpResponseStatus status,ByteBuf data){
  sendHttp2Response0(status,true,data);
}
