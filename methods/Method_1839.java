/** 
 * Returns a cloned reference to the stored encoded bytes. <p>The caller has to close the reference once it has finished using it.
 */
public CloseableReference<PooledByteBuffer> getByteBufferRef(){
  return CloseableReference.cloneOrNull(mPooledByteBufferRef);
}
