/** 
 * Cleans unnecessary whitespaces.
 */
@Override public void text(final CharSequence text){
  if (!strip) {
    super.text(text);
    return;
  }
  int textLength=text.length();
  char[] dest=new char[textLength];
  int ndx=0;
  boolean regularChar=true;
  for (int i=0; i < textLength; i++) {
    char c=text.charAt(i);
    if (CharUtil.isWhitespace(c)) {
      if (regularChar) {
        regularChar=false;
        c=' ';
      }
 else {
        continue;
      }
    }
 else {
      regularChar=true;
    }
    dest[ndx]=c;
    ndx++;
  }
  if (regularChar || (ndx != 1)) {
    super.text(CharBuffer.wrap(dest,0,ndx));
    strippedCharsCount+=textLength - ndx;
  }
 else {
    strippedCharsCount+=textLength;
  }
}
