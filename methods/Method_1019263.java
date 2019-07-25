@SuppressWarnings("unchecked") @Override public <T>TypeAdapter<T> create(Gson gson,TypeToken<T> typeToken){
  Type type=typeToken.getType();
  if (!CLASSES.contains(typeToken.getRawType()) || !(type instanceof ParameterizedType)) {
    return null;
  }
  com.google.common.reflect.TypeToken<SortedMap<?,?>> betterToken=(com.google.common.reflect.TypeToken<SortedMap<?,?>>)com.google.common.reflect.TypeToken.of(typeToken.getType());
  final TypeAdapter<Map<?,?>> mapAdapter=(TypeAdapter<Map<?,?>>)gson.getAdapter(TypeToken.get(betterToken.getSupertype(Map.class).getType()));
  return new TypeAdapter<T>(){
    @Override public void write(    JsonWriter out,    T value) throws IOException {
      TreeMap<?,?> treeMap=Maps.newTreeMap((SortedMap<?,?>)value);
      mapAdapter.write(out,treeMap);
    }
    @SuppressWarnings("rawtypes") @Override public T read(    JsonReader in) throws IOException {
      TreeMap treeMap=Maps.newTreeMap();
      treeMap.putAll(mapAdapter.read(in));
      return (T)treeMap;
    }
  }
;
}
