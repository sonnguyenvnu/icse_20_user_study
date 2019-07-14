/** 
 * Inserts one array into another <code>short</code> array.
 */
public static short[] insert(short[] dest,short[] src,int offset){
  short[] temp=new short[dest.length + src.length];
  System.arraycopy(dest,0,temp,0,offset);
  System.arraycopy(src,0,temp,offset,src.length);
  System.arraycopy(dest,offset,temp,src.length + offset,dest.length - offset);
  return temp;
}
