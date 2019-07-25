@Nonnull @Override public Map<String,String> convert(@Nonnull final String value){
  final Map<String,String> map=new HashMap<>();
  for (  final String entry : value.split(";")) {
    if (!entry.isEmpty()) {
      final String[] parts=entry.split("=");
      map.put(parts[0],parts[1]);
    }
  }
  return map;
}
