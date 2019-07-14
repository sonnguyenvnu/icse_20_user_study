public Response<List<Object>> exec(){
  if (currentMulti == null)   throw new JedisDataException("EXEC without MULTI");
  client.exec();
  Response<List<Object>> response=super.getResponse(currentMulti);
  currentMulti.setResponseDependency(response);
  currentMulti=null;
  return response;
}
