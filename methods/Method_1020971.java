void apply(@NonNull final Map<String,Target> map){
  checkNotNull(map);
  targetMap.set(new HashMap<>(map));
}
