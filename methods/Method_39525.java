/** 
 * Puts an UTF8 string into this byte vector. The byte vector is automatically enlarged if necessary. The string length is encoded in two bytes before the encoded characters, if there is space for that (i.e. if this.length - offset - 2 &gt;= 0).
 * @param stringValue the String to encode.
 * @param offset the index of the first character to encode. The previous characters are supposedto have already been encoded, using only one byte per character.
 * @param maxByteLength the maximum byte length of the encoded string, including the alreadyencoded characters.
 * @return this byte vector.
 */
final ByteVector encodeUtf8(final String stringValue,final int offset,final int maxByteLength){
  int charLength=stringValue.length();
  int byteLength=offset;
  for (int i=offset; i < charLength; ++i) {
    char charValue=stringValue.charAt(i);
    if (charValue >= 0x0001 && charValue <= 0x007F) {
      byteLength++;
    }
 else     if (charValue <= 0x07FF) {
      byteLength+=2;
    }
 else {
      byteLength+=3;
    }
  }
  if (byteLength > maxByteLength) {
    throw new IllegalArgumentException("UTF8 string too large");
  }
  int byteLengthOffset=length - offset - 2;
  if (byteLengthOffset >= 0) {
    data[byteLengthOffset]=(byte)(byteLength >>> 8);
    data[byteLengthOffset + 1]=(byte)byteLength;
  }
  if (length + byteLength - offset > data.length) {
    enlarge(byteLength - offset);
  }
  int currentLength=length;
  for (int i=offset; i < charLength; ++i) {
    char charValue=stringValue.charAt(i);
    if (charValue >= 0x0001 && charValue <= 0x007F) {
      data[currentLength++]=(byte)charValue;
    }
 else     if (charValue <= 0x07FF) {
      data[currentLength++]=(byte)(0xC0 | charValue >> 6 & 0x1F);
      data[currentLength++]=(byte)(0x80 | charValue & 0x3F);
    }
 else {
      data[currentLength++]=(byte)(0xE0 | charValue >> 12 & 0xF);
      data[currentLength++]=(byte)(0x80 | charValue >> 6 & 0x3F);
      data[currentLength++]=(byte)(0x80 | charValue & 0x3F);
    }
  }
  length=currentLength;
  return this;
}
