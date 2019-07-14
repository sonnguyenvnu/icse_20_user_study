@Nonnull @Override public BufferedSource toJson(@Nonnull Parsed value){
  try {
    return Okio.buffer(Okio.source(new ByteArrayInputStream(objectMapper.writeValueAsBytes(value))));
  }
 catch (  JsonProcessingException e) {
    throw Exceptions.propagate(e);
  }
}
