/** 
 * Inserts one array into another by replacing specified offset.
 */
public static byte[] insertAt(byte[] dest,byte[] src,int offset){
  byte[] temp=new byte[dest.length + src.length - 1];
  System.arraycopy(dest,0,temp,0,offset);
  System.arraycopy(src,0,temp,offset,src.length);
  System.arraycopy(dest,offset + 1,temp,src.length + offset,dest.length - offset - 1);
  return temp;
}
