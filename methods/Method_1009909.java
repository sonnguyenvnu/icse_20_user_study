@Override public final <T>API with(MetadataKey<T> key,@Nullable T value){
  checkNotNull(key,"metadata key");
  if (value != null) {
    addMetadata(key,value);
  }
  return api();
}
