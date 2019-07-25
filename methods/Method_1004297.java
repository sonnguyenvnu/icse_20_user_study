public static CacheKey from(@NotNull String key){
  return new CacheKey(checkNotNull(key,"key == null"));
}
