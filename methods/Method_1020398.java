/** 
 * Creates a  {@code Tag} from the given key and value.<p>For backwards-compatibility this method still produces propagating  {@link Tag}s. <p>This is equivalent to calling  {@code create(key, value,TagMetadata.create(TagTtl.UNLIMITED_PROPAGATION))}.
 * @param key the tag key.
 * @param value the tag value.
 * @return a {@code Tag} with the given key and value.
 * @since 0.8
 * @deprecated in favor of {@link #create(TagKey,TagValue,TagMetadata)}.
 */
@Deprecated public static Tag create(TagKey key,TagValue value){
  return create(key,value,METADATA_UNLIMITED_PROPAGATION);
}
