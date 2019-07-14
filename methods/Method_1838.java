/** 
 * Returns true if the internal buffer reference is valid or the InputStream Supplier is not null, false otherwise.
 */
public synchronized boolean isValid(){
  return CloseableReference.isValid(mPooledByteBufferRef) || mInputStreamSupplier != null;
}
