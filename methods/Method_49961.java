/** 
 * Append to Text-string.
 * @param textString the textString to append
 * @throws NullPointerException if the text String is nullor an IOException occured.
 */
public void appendTextString(byte[] textString){
  if (null == textString) {
    throw new NullPointerException("Text-string is null.");
  }
  if (null == mData) {
    mData=new byte[textString.length];
    System.arraycopy(textString,0,mData,0,textString.length);
  }
 else {
    ByteArrayOutputStream newTextString=new ByteArrayOutputStream();
    try {
      newTextString.write(mData);
      newTextString.write(textString);
    }
 catch (    IOException e) {
      Timber.e(e,"logging error");
      e.printStackTrace();
      throw new NullPointerException("appendTextString: failed when write a new Text-string");
    }
    mData=newTextString.toByteArray();
  }
}
