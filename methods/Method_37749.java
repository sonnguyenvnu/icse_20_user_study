/** 
 * Resizes a <code>int</code> array.
 */
public static int[] resize(int[] buffer,int newSize){
  int[] temp=new int[newSize];
  System.arraycopy(buffer,0,temp,0,buffer.length >= newSize ? newSize : buffer.length);
  return temp;
}
