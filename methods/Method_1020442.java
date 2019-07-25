@Override public <C>void inject(TagContext tagContext,C carrier,Setter<C> setter) throws TagContextSerializationException {
  checkNotNull(tagContext,"tagContext");
  checkNotNull(carrier,"carrier");
  checkNotNull(setter,"setter");
  if (State.DISABLED.equals(state.getInternal())) {
    return;
  }
  try {
    StringBuilder stringBuilder=new StringBuilder(TAGCONTEXT_SERIALIZED_SIZE_LIMIT);
    int totalChars=0;
    int totalTags=0;
    for (Iterator<Tag> i=InternalUtils.getTags(tagContext); i.hasNext(); ) {
      Tag tag=i.next();
      if (TagTtl.NO_PROPAGATION.equals(tag.getTagMetadata().getTagTtl())) {
        continue;
      }
      if (stringBuilder.length() > 0) {
        stringBuilder.append(TAG_DELIMITER);
      }
      totalTags++;
      totalChars+=encodeTag(tag,stringBuilder);
    }
    checkArgument(totalTags <= MAX_NUMBER_OF_TAGS,"Number of tags in the TagContext exceeds limit " + MAX_NUMBER_OF_TAGS);
    checkArgument(totalChars <= TAGCONTEXT_SERIALIZED_SIZE_LIMIT,"Size of TagContext exceeds the maximum serialized size " + TAGCONTEXT_SERIALIZED_SIZE_LIMIT);
    setter.put(carrier,CORRELATION_CONTEXT,stringBuilder.toString());
  }
 catch (  IllegalArgumentException e) {
    throw new TagContextSerializationException("Failed to serialize TagContext",e);
  }
}
