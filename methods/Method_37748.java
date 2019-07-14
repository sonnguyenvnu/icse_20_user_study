/** 
 * Resizes a <code>short</code> array.
 */
public static short[] resize(short[] buffer,int newSize){
  short[] temp=new short[newSize];
  System.arraycopy(buffer,0,temp,0,buffer.length >= newSize ? newSize : buffer.length);
  return temp;
}
