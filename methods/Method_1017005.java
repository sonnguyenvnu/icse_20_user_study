@Override protected Map<String,String> key(final Map<String,String> tags){
  return getOf().map(of -> {
    if (of.isEmpty()) {
      return ALL_GROUP;
    }
    final Map<String,String> key=new HashMap<>();
    for (    final String o : of) {
      String value=tags.get(o);
      if (value == null) {
        continue;
      }
      key.put(o,value);
    }
    return key;
  }
).orElse(tags);
}
