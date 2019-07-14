@Override public int build(TreeMap<String,V> keyValueMap){
  for (  Map.Entry<String,V> entry : keyValueMap.entrySet()) {
    put(entry.getKey(),entry.getValue());
  }
  return 0;
}
