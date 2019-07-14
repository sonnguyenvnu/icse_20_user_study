@NonNull public Map<String,Object> toMap(){
  final Map<String,Object> map=new HashMap<>(content.length());
  for (final Iterator<String> iterator=content.keys(); iterator.hasNext(); ) {
    final String key=iterator.next();
    map.put(key,get(key));
  }
  return map;
}
