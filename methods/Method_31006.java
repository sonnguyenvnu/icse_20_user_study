@Nullable public static <K,V>V computeIfAbsent(@NonNull Map<K,V> map,@Nullable K key,@NonNull Function<? super K,? extends V> mappingFunction){
  Objects.requireNonNull(mappingFunction);
  V value=map.get(key);
  if (value == null) {
    V newValue=mappingFunction.apply(key);
    if (newValue != null) {
      map.put(key,newValue);
      return newValue;
    }
  }
  return value;
}
