private void put(String key,String value){
  if (key != null) {
    System.out.println(key + "=" + value);
    value=value.trim();
    if (key.contains(".")) {
      List<String> keyList=KeyUtil.getKeyList(key);
      int size=keyList.size();
      Map<String,Object> mapObject=map;
      int length=size - 1;
      for (int i=0; i < length; i++) {
        String k=keyList.get(i);
        Object o=mapObject.get(k);
        if (o == null) {
          o=new ConcurrentHashMap<String,Object>();
          mapObject.put(k,o);
        }
        if (mapObject instanceof Map) {
          mapObject=(Map<String,Object>)o;
        }
 else {
          System.err.println("_________Config unhappy for x7-config's map:  key = " + k);
        }
      }
      if (mapObject instanceof Map) {
        mapObject.put(keyList.get(length),value);
      }
 else {
        System.err.println("_________Config unhappy for x7-config's map:  key = " + keyList.get(length));
      }
    }
 else {
      map.put(key,value);
    }
  }
}
