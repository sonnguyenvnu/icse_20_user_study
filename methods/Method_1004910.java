@Override public Map<String,Object> _apply(final Element element){
  final Map<String,Object> map=new LinkedHashMap<>(fields.size() + constants.size());
  for (  final Map.Entry<String,String> entry : fields.entrySet()) {
    final Object value=getFieldValue(element,entry.getKey());
    if (null != value) {
      map.put(entry.getValue(),value);
    }
  }
  map.putAll(constants);
  return map;
}
