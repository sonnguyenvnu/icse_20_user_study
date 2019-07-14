/** 
 * Puts two bytes and a short into this byte vector. The byte vector is automatically enlarged if necessary.
 * @param byteValue1 a byte.
 * @param byteValue2 another byte.
 * @param shortValue a short.
 * @return this byte vector.
 */
final ByteVector put112(final int byteValue1,final int byteValue2,final int shortValue){
  int currentLength=length;
  if (currentLength + 4 > data.length) {
    enlarge(4);
  }
  byte[] currentData=data;
  currentData[currentLength++]=(byte)byteValue1;
  currentData[currentLength++]=(byte)byteValue2;
  currentData[currentLength++]=(byte)(shortValue >>> 8);
  currentData[currentLength++]=(byte)shortValue;
  length=currentLength;
  return this;
}
