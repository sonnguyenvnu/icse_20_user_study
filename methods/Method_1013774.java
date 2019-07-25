@Override protected ProxyPongEntity format(Object payload){
  String response=payloadToString(payload);
  validResponse(response);
  return formatResponse(response);
}
