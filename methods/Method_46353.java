private String statKeySplit(StatMapKey statKey){
  JsonStringBuilder jsonBufferKey=new JsonStringBuilder();
  Map<String,String> keyMap=statKey.getKeyMap();
  jsonBufferKey.appendBegin();
  for (  Map.Entry<String,String> entry : keyMap.entrySet()) {
    jsonBufferKey.append(entry.getKey(),entry.getValue());
  }
  jsonBufferKey.appendEnd(false);
  return jsonBufferKey.toString();
}
