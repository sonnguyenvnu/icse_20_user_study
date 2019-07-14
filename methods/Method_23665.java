/** 
 * Get this entire array as a double array. Everything must be an double. 
 */
public double[] getDoubleArray(){
  double[] outgoing=new double[size()];
  for (int i=0; i < size(); i++) {
    outgoing[i]=getDouble(i);
  }
  return outgoing;
}
