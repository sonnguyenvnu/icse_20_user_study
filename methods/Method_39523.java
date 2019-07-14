/** 
 * Puts one byte and two shorts into this byte vector. The byte vector is automatically enlarged if necessary.
 * @param byteValue a byte.
 * @param shortValue1 a short.
 * @param shortValue2 another short.
 * @return this byte vector.
 */
final ByteVector put122(final int byteValue,final int shortValue1,final int shortValue2){
  int currentLength=length;
  if (currentLength + 5 > data.length) {
    enlarge(5);
  }
  byte[] currentData=data;
  currentData[currentLength++]=(byte)byteValue;
  currentData[currentLength++]=(byte)(shortValue1 >>> 8);
  currentData[currentLength++]=(byte)shortValue1;
  currentData[currentLength++]=(byte)(shortValue2 >>> 8);
  currentData[currentLength++]=(byte)shortValue2;
  length=currentLength;
  return this;
}
