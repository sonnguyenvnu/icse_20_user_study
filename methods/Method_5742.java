/** 
 * Calculates a hash code for the header of this  {@code CachedContent} which is compatible withthe index file with  {@code version}.
 */
public int headerHashCode(int version){
  int result=id;
  result=31 * result + key.hashCode();
  if (version < VERSION_METADATA_INTRODUCED) {
    long length=ContentMetadata.getContentLength(metadata);
    result=31 * result + (int)(length ^ (length >>> 32));
  }
 else {
    result=31 * result + metadata.hashCode();
  }
  return result;
}
