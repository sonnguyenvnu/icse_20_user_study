/** 
 * Returns a copy  {@link DefaultContentMetadata} with {@code mutations} applied. If {@code mutations} don't change anything, returns this instance.
 */
public DefaultContentMetadata copyWithMutationsApplied(ContentMetadataMutations mutations){
  Map<String,byte[]> mutatedMetadata=applyMutations(metadata,mutations);
  if (isMetadataEqual(metadata,mutatedMetadata)) {
    return this;
  }
  return new DefaultContentMetadata(mutatedMetadata);
}
