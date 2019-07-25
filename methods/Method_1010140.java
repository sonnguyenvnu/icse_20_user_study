@Nullable public String value(@Nullable String origin){
  if (origin == null) {
    return null;
  }
  String cached=myStrings.get(origin);
  if (cached != null) {
    return cached;
  }
  myStrings.put(origin,origin);
  return origin;
}
