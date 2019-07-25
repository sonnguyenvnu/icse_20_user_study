private final JsonDeserializer<?> _find(JavaType type){
  if (_classMappings == null) {
    return null;
  }
  return _classMappings.get(new ClassKey(type.getRawClass()));
}
