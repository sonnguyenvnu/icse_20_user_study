/** 
 * Puts a byte and a short into this byte vector. The byte vector is automatically enlarged if necessary.
 * @param byteValue a byte.
 * @param shortValue a short.
 * @return this byte vector.
 */
final ByteVector put12(final int byteValue,final int shortValue){
  int currentLength=length;
  if (currentLength + 3 > data.length) {
    enlarge(3);
  }
  byte[] currentData=data;
  currentData[currentLength++]=(byte)byteValue;
  currentData[currentLength++]=(byte)(shortValue >>> 8);
  currentData[currentLength++]=(byte)shortValue;
  length=currentLength;
  return this;
}
