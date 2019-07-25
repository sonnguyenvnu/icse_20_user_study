/** 
 * This operation is not supported by URL Blob Store
 */
@Override public void delete(BlobPath path){
  throw new UnsupportedOperationException("URL repository is read only");
}
