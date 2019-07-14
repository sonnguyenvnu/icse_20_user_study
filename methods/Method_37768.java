/** 
 * Inserts one element into another <code>short</code> array.
 */
public static short[] insert(short[] dest,short src,int offset){
  short[] temp=new short[dest.length + 1];
  System.arraycopy(dest,0,temp,0,offset);
  temp[offset]=src;
  System.arraycopy(dest,offset,temp,offset + 1,dest.length - offset);
  return temp;
}
