@Override public List<Boolean> scriptExists(String... sha1){
  client.scriptExists(sha1);
  List<Long> result=client.getIntegerMultiBulkReply();
  List<Boolean> exists=new ArrayList<Boolean>();
  for (  Long value : result)   exists.add(value == 1);
  return exists;
}
