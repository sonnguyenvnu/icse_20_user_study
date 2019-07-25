public Settings merge(Map<String,String> map){
  if (map == null || map.isEmpty()) {
    return this;
  }
  for (  Entry<String,String> entry : map.entrySet()) {
    setProperty(entry.getKey(),entry.getValue());
  }
  return this;
}
