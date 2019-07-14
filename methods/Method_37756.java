/** 
 * Removes sub-array from <code>double</code> array.
 */
public static double[] remove(double[] buffer,int offset,int length){
  int len2=buffer.length - length;
  double[] temp=new double[len2];
  System.arraycopy(buffer,0,temp,0,offset);
  System.arraycopy(buffer,offset + length,temp,offset,len2 - offset);
  return temp;
}
