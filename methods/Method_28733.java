public List<Object> getMany(final int count){
  flush();
  final List<Object> responses=new ArrayList<Object>(count);
  for (int i=0; i < count; i++) {
    try {
      responses.add(readProtocolWithCheckingBroken());
    }
 catch (    JedisDataException e) {
      responses.add(e);
    }
  }
  return responses;
}
