private Optional<Charset> toCharset(final MediaType type){
  if (charset != null) {
    return Optional.of(charset);
  }
  if (type == null) {
    return of(Charsets.UTF_8);
  }
  return type.charset();
}
