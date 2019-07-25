@Override protected Map<String,String> key(final Map<String,String> tags){
  return getOf().map(of -> {
    if (of.isEmpty()) {
      return tags;
    }
    final Map<String,String> key=new HashMap<>(tags);
    for (    final String o : of) {
      key.remove(o);
    }
    return key;
  }
).orElse(NO_GROUP);
}
