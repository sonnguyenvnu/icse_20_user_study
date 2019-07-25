public static Builder builder(@NotNull String key){
  return new Builder(checkNotNull(key,"key == null"),new LinkedHashMap<String,Object>(),null);
}
