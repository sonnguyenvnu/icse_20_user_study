/** 
 * Resizes a <code>char</code> array.
 */
public static char[] resize(char[] buffer,int newSize){
  char[] temp=new char[newSize];
  System.arraycopy(buffer,0,temp,0,buffer.length >= newSize ? newSize : buffer.length);
  return temp;
}
