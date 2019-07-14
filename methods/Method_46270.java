@Override protected void sendRpcError(HttpResponseStatus status,ByteBuf data){
  sendHttp1Response0(status,true,data);
}
