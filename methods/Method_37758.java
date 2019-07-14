/** 
 * Returns subarray.
 */
public static byte[] subarray(byte[] buffer,int offset,int length){
  byte[] temp=new byte[length];
  System.arraycopy(buffer,offset,temp,0,length);
  return temp;
}
