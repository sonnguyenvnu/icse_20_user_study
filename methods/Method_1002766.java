private static FieldLocation location(AnnotatedValueResolver resolver){
  if (resolver.isPathVariable()) {
    return PATH;
  }
  if (resolver.annotationType() == Param.class) {
    return QUERY;
  }
  if (resolver.annotationType() == Header.class) {
    return HEADER;
  }
  return UNSPECIFIED;
}
