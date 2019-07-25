public static void destroy(HashMap map){
  for (Iterator keyit=map.keySet().iterator(); keyit.hasNext(); keyit.remove()) {
    Object key=keyit.next();
    Object value=map.get(key);
    if (value instanceof HashMap) {
      HashMap valueMap=(HashMap)value;
      destroy(valueMap);
    }
  }
}
