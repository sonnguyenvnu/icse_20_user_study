/** 
 * Adds the key/value pair and metadata regardless of whether the key is present.
 * @param key the {@code TagKey} which will be set.
 * @param value the {@code TagValue} to set for the given key.
 * @param tagMetadata the {@code TagMetadata} associated with this {@link Tag}.
 * @return this
 * @since 0.20
 */
public TagContextBuilder put(TagKey key,TagValue value,TagMetadata tagMetadata){
  @SuppressWarnings("deprecation") TagContextBuilder builder=put(key,value);
  return builder;
}
