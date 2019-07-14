public List<Object> exec(){
  client.getMany(getPipelinedResponseLength());
  client.exec();
  inTransaction=false;
  List<Object> unformatted=client.getObjectMultiBulkReply();
  if (unformatted == null) {
    return null;
  }
  List<Object> formatted=new ArrayList<Object>();
  for (  Object o : unformatted) {
    try {
      formatted.add(generateResponse(o).get());
    }
 catch (    JedisDataException e) {
      formatted.add(e);
    }
  }
  return formatted;
}
