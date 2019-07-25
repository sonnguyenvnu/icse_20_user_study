public MessageLogDto put(String key,Object value){
  messageBodyMap.put(key,value);
  return this;
}
