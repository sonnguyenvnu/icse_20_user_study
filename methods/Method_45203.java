public static RequestExtractor<Object> var(final Object text){
  return new PlainExtractor<>(checkNotNull(text,"Template variable should not be null or empty"));
}
