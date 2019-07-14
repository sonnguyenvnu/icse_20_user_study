/** 
 * Validates that the ByteBuffer instance is valid (aka not closed). If it is closed, then we raise a ClosedException This doesn't really need to be synchronized, but lint won't shut up otherwise
 * @throws ClosedException
 */
synchronized void ensureValid(){
  if (isClosed()) {
    throw new ClosedException();
  }
}
