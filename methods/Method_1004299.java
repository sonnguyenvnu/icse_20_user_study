@Override public Map<Class,Map<String,Record>> dump(){
  Map<Class,Map<String,Record>> dump=new LinkedHashMap<>();
  dump.put(this.getClass(),Collections.unmodifiableMap(new LinkedHashMap<>(lruCache.asMap())));
  if (nextCache().isPresent()) {
    dump.putAll(nextCache().get().dump());
  }
  return dump;
}
