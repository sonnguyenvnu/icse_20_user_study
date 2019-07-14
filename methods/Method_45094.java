public static String asHeaders(final HttpMessage message){
  return HEAD_JOINER.join(from(message.getHeaders().entrySet()).transformAndConcat(toMapEntries()));
}
