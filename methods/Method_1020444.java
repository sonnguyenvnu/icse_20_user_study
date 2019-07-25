@Override public TagContextBuilder put(TagKey key,TagValue value,TagMetadata tagMetadata){
  TagValueWithMetadata valueWithMetadata=TagValueWithMetadata.create(checkNotNull(value,"value"),checkNotNull(tagMetadata,"tagMetadata"));
  tags.put(checkNotNull(key,"key"),valueWithMetadata);
  return this;
}
