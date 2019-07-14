public List<Response<?>> execGetResponse(){
  client.getMany(getPipelinedResponseLength());
  client.exec();
  inTransaction=false;
  List<Object> unformatted=client.getObjectMultiBulkReply();
  if (unformatted == null) {
    return null;
  }
  List<Response<?>> response=new ArrayList<Response<?>>();
  for (  Object o : unformatted) {
    response.add(generateResponse(o));
  }
  return response;
}
