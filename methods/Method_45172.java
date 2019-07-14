public static ImmutableMap<String,Resource> toHeaders(final Iterable<HttpHeader> headers){
  ImmutableMap.Builder<String,Resource> builder=ImmutableMap.builder();
  for (  HttpHeader header : headers) {
    builder.put(header.getName(),header.getValue());
  }
  return builder.build();
}
