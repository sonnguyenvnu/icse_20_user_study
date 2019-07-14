/** 
 * Writes to disk cache
 * @throws IOException
 */
private void writeToDiskCache(final CacheKey key,final EncodedImage encodedImage){
  FLog.v(TAG,"About to write to disk-cache for key %s",key.getUriString());
  try {
    mFileCache.insert(key,new WriterCallback(){
      @Override public void write(      OutputStream os) throws IOException {
        mPooledByteStreams.copy(encodedImage.getInputStream(),os);
      }
    }
);
    FLog.v(TAG,"Successful disk-cache write for key %s",key.getUriString());
  }
 catch (  IOException ioe) {
    FLog.w(TAG,ioe,"Failed to write to disk-cache for key %s",key.getUriString());
  }
}
