/** 
 * Applies  {@code mutations} to the {@link ContentMetadata} for the given key. A new {@link CachedContent} is added if there isn't one already with the given key.
 */
public void applyContentMetadataMutations(String key,ContentMetadataMutations mutations){
  CachedContent cachedContent=getOrAdd(key);
  if (cachedContent.applyMetadataMutations(mutations)) {
    changed=true;
  }
}
