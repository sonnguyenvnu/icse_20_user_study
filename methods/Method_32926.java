/** 
 * Write the Unicode code point to the terminal encoded in UTF-8. 
 */
public void writeCodePoint(boolean prependEscape,int codePoint){
  if (codePoint > 1114111 || (codePoint >= 0xD800 && codePoint <= 0xDFFF)) {
    throw new IllegalArgumentException("Invalid code point: " + codePoint);
  }
  int bufferPosition=0;
  if (prependEscape)   mUtf8InputBuffer[bufferPosition++]=27;
  if (codePoint <= 0b1111111) {
    mUtf8InputBuffer[bufferPosition++]=(byte)codePoint;
  }
 else   if (codePoint <= 0b11111111111) {
    mUtf8InputBuffer[bufferPosition++]=(byte)(0b11000000 | (codePoint >> 6));
    mUtf8InputBuffer[bufferPosition++]=(byte)(0b10000000 | (codePoint & 0b111111));
  }
 else   if (codePoint <= 0b1111111111111111) {
    mUtf8InputBuffer[bufferPosition++]=(byte)(0b11100000 | (codePoint >> 12));
    mUtf8InputBuffer[bufferPosition++]=(byte)(0b10000000 | ((codePoint >> 6) & 0b111111));
    mUtf8InputBuffer[bufferPosition++]=(byte)(0b10000000 | (codePoint & 0b111111));
  }
 else {
    mUtf8InputBuffer[bufferPosition++]=(byte)(0b11110000 | (codePoint >> 18));
    mUtf8InputBuffer[bufferPosition++]=(byte)(0b10000000 | ((codePoint >> 12) & 0b111111));
    mUtf8InputBuffer[bufferPosition++]=(byte)(0b10000000 | ((codePoint >> 6) & 0b111111));
    mUtf8InputBuffer[bufferPosition++]=(byte)(0b10000000 | (codePoint & 0b111111));
  }
  write(mUtf8InputBuffer,0,bufferPosition);
}
