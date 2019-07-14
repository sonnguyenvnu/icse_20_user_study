public ReadConfiguration asReadConfiguration(){
  final Map<String,Object> entries=toMap();
  return new ReadConfiguration(){
    @Override public <O>O get(    String key,    Class<O> dataType){
      Preconditions.checkArgument(!entries.containsKey(key) || dataType.isAssignableFrom(entries.get(key).getClass()));
      return (O)entries.get(key);
    }
    @Override public Iterable<String> getKeys(    final String prefix){
      final boolean prefixBlank=StringUtils.isBlank(prefix);
      return entries.keySet().stream().filter(s -> prefixBlank || s.startsWith(prefix)).collect(Collectors.toList());
    }
    @Override public void close(){
    }
  }
;
}
