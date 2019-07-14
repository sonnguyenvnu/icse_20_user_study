/** 
 * Called when the  {@link HlsSampleStreamWrapper} has finished loading a chunk obtained from thissource.
 * @param chunk The chunk whose load has been completed.
 */
public void onChunkLoadCompleted(Chunk chunk){
  if (chunk instanceof EncryptionKeyChunk) {
    EncryptionKeyChunk encryptionKeyChunk=(EncryptionKeyChunk)chunk;
    scratchSpace=encryptionKeyChunk.getDataHolder();
    setEncryptionData(encryptionKeyChunk.dataSpec.uri,encryptionKeyChunk.iv,encryptionKeyChunk.getResult());
  }
}
