public <V>void setProperty(String key,V value,Map<String,Integer> keyMap){
  assert !keyMap.isEmpty() && keyMap.containsKey(key);
  if (keyMap.size() == 1)   properties=value;
 else   ((Object[])properties)[keyMap.get(key)]=value;
}
