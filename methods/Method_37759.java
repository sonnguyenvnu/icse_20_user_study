/** 
 * Returns subarray.
 */
public static int[] subarray(int[] buffer,int offset,int length){
  int[] temp=new int[length];
  System.arraycopy(buffer,offset,temp,0,length);
  return temp;
}
