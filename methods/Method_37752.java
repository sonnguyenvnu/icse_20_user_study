/** 
 * Removes sub-array from <code>byte</code> array.
 */
public static byte[] remove(byte[] buffer,int offset,int length){
  int len2=buffer.length - length;
  byte[] temp=new byte[len2];
  System.arraycopy(buffer,0,temp,0,offset);
  System.arraycopy(buffer,offset + length,temp,offset,len2 - offset);
  return temp;
}
