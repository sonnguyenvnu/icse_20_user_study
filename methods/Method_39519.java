/** 
 * Puts two bytes into this byte vector. The byte vector is automatically enlarged if necessary.
 * @param byteValue1 a byte.
 * @param byteValue2 another byte.
 * @return this byte vector.
 */
final ByteVector put11(final int byteValue1,final int byteValue2){
  int currentLength=length;
  if (currentLength + 2 > data.length) {
    enlarge(2);
  }
  byte[] currentData=data;
  currentData[currentLength++]=(byte)byteValue1;
  currentData[currentLength++]=(byte)byteValue2;
  length=currentLength;
  return this;
}
