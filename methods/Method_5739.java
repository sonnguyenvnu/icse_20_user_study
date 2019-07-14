/** 
 * Applies  {@code mutations} to the metadata.
 * @return Whether {@code mutations} changed any metadata.
 */
public boolean applyMetadataMutations(ContentMetadataMutations mutations){
  DefaultContentMetadata oldMetadata=metadata;
  metadata=metadata.copyWithMutationsApplied(mutations);
  return !metadata.equals(oldMetadata);
}
