@Nullable private String sanitize(String key,@Nullable String value){
  if (value == null) {
    return null;
  }
  boolean matchesAnyPattern=Arrays.stream(this.keysToSanitize).anyMatch(pattern -> pattern.matcher(key).matches());
  return matchesAnyPattern ? "******" : value;
}
