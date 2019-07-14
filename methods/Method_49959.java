/** 
 * Get Text-string value.
 * @return the value
 */
public byte[] getTextString(){
  byte[] byteArray=new byte[mData.length];
  System.arraycopy(mData,0,byteArray,0,mData.length);
  return byteArray;
}
