/** 
 * Puts an int into this byte vector. The byte vector is automatically enlarged if necessary.
 * @param intValue an int.
 * @return this byte vector.
 */
public ByteVector putInt(final int intValue){
  int currentLength=length;
  if (currentLength + 4 > data.length) {
    enlarge(4);
  }
  byte[] currentData=data;
  currentData[currentLength++]=(byte)(intValue >>> 24);
  currentData[currentLength++]=(byte)(intValue >>> 16);
  currentData[currentLength++]=(byte)(intValue >>> 8);
  currentData[currentLength++]=(byte)intValue;
  length=currentLength;
  return this;
}
