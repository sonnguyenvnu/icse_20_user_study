/** 
 * throws  {@link UnsupportedOperationException}
 */
@Override public long getAndSet(long newValue){
  throw new UnsupportedOperationException(OPERATION_NOT_SUPPORTED_MSG);
}
