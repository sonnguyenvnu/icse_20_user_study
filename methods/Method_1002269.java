synchronized Map<String,Object> load() throws Exception {
  Config config=config();
  Map<String,Object> map=new HashMap<String,Object>();
  for (  Map.Entry<String,ConfigValue> entry : config.entrySet()) {
    String key=entry.getKey();
    Object value=entry.getValue().unwrapped();
    if (value instanceof List) {
      if (false == safeArrayKeyExpansion(config,key)) {
        log.error("Unable to expand array: {}",key);
        continue;
      }
      List values=(List)value;
      map.put(lengthKey(key),values.size());
      for (int i=0; i < values.size(); i++) {
        map.put(indexedKey(key,i),values.get(i));
      }
    }
 else {
      map.put(key,value);
    }
  }
  return map;
}
