/** 
 * Inserts one array into another <code>char</code> array.
 */
public static char[] insert(char[] dest,char[] src,int offset){
  char[] temp=new char[dest.length + src.length];
  System.arraycopy(dest,0,temp,0,offset);
  System.arraycopy(src,0,temp,offset,src.length);
  System.arraycopy(dest,offset,temp,src.length + offset,dest.length - offset);
  return temp;
}
