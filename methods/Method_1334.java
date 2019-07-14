/** 
 * Returns the number of bytes still available to read
 */
@Override public int available(){
  return mPooledByteBuffer.size() - mOffset;
}
