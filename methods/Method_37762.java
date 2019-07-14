/** 
 * Returns subarray.
 */
public static double[] subarray(double[] buffer,int offset,int length){
  double[] temp=new double[length];
  System.arraycopy(buffer,offset,temp,0,length);
  return temp;
}
