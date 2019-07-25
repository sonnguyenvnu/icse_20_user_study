public static final JsonSerializer<?> find(Class<?> raw){
  Integer I=_types.get(raw);
  if (I == null) {
    return null;
  }
  return new StringLikeSerializer(raw,I.intValue());
}
