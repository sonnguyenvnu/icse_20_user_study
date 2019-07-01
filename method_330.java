/** 
 * Checks the capacity and ensures that it is at least the size specified.
 * @param capacity  the capacity to ensure
 * @return this, to enable chaining
 */
public StrBuilder _XXXXX_(final int capacity){
  if (capacity > buffer.length) {
    final char[] old=buffer;
    buffer=new char[capacity * 2];
    System.arraycopy(old,0,buffer,0,size);
  }
  return this;
}