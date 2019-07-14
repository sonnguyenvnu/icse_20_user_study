/** 
 * Inserts one array into another <code>byte</code> array.
 */
public static byte[] insert(byte[] dest,byte[] src,int offset){
  byte[] temp=new byte[dest.length + src.length];
  System.arraycopy(dest,0,temp,0,offset);
  System.arraycopy(src,0,temp,offset,src.length);
  System.arraycopy(dest,offset,temp,src.length + offset,dest.length - offset);
  return temp;
}
