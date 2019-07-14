@Nullable String findKeyForValue(@NonNull ArrayMap<String,String> map,@NonNull String value){
  final int numElements=map.size();
  for (int i=0; i < numElements; i++) {
    if (value.equals(map.valueAt(i))) {
      return map.keyAt(i);
    }
  }
  return null;
}
