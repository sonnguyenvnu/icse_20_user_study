@NonNull public static <K,V,M extends Map<K,V>>MapBuilder<K,V,M> buildUpon(@NonNull M map){
  return new MapBuilder<>(map);
}
