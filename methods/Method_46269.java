@Override protected void sendAppError(HttpResponseStatus status,ByteBuf data){
  sendHttp1Response0(status,true,data);
}
