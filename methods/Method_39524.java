/** 
 * Puts an UTF8 string into this byte vector. The byte vector is automatically enlarged if necessary.
 * @param stringValue a String whose UTF8 encoded length must be less than 65536.
 * @return this byte vector.
 */
public ByteVector putUTF8(final String stringValue){
  int charLength=stringValue.length();
  if (charLength > 65535) {
    throw new IllegalArgumentException("UTF8 string too large");
  }
  int currentLength=length;
  if (currentLength + 2 + charLength > data.length) {
    enlarge(2 + charLength);
  }
  byte[] currentData=data;
  currentData[currentLength++]=(byte)(charLength >>> 8);
  currentData[currentLength++]=(byte)charLength;
  for (int i=0; i < charLength; ++i) {
    char charValue=stringValue.charAt(i);
    if (charValue >= '\u0001' && charValue <= '\u007F') {
      currentData[currentLength++]=(byte)charValue;
    }
 else {
      length=currentLength;
      return encodeUtf8(stringValue,i,65535);
    }
  }
  length=currentLength;
  return this;
}
