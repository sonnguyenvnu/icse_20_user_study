@Override public <C>TagContext extract(C carrier,Getter<C> getter) throws TagContextDeserializationException {
  checkNotNull(carrier,"carrier");
  checkNotNull(getter,"getter");
  if (State.DISABLED.equals(state.getInternal())) {
    return TagMapImpl.EMPTY;
  }
  @Nullable String correlationContext=getter.get(carrier,CORRELATION_CONTEXT);
  if (correlationContext == null) {
    throw new TagContextDeserializationException(CORRELATION_CONTEXT + " not present.");
  }
  try {
    if (correlationContext.isEmpty()) {
      return TagMapImpl.EMPTY;
    }
    Map<TagKey,TagValueWithMetadata> tags=new HashMap<>();
    List<String> stringTags=TAG_SPLITTER.splitToList(correlationContext);
    for (    String stringTag : stringTags) {
      decodeTag(stringTag,tags);
    }
    return new TagMapImpl(tags);
  }
 catch (  IllegalArgumentException e) {
    throw new TagContextDeserializationException("Invalid TagContext: " + correlationContext,e);
  }
}
