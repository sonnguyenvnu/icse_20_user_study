/** 
 * ??????,?????????????????????
 */
public void extend(int ordinaryMax){
  this.ordinaryMax=ordinaryMax;
  double[][] n_transititon_probability=new double[ordinaryMax][ordinaryMax];
  for (int i=0; i < transititon_probability.length; i++) {
    System.arraycopy(transititon_probability[i],0,n_transititon_probability[i],0,transititon_probability.length);
  }
  transititon_probability=n_transititon_probability;
  int[] n_total=new int[ordinaryMax];
  System.arraycopy(total,0,n_total,0,total.length);
  total=n_total;
  double[] n_start_probability=new double[ordinaryMax];
  System.arraycopy(start_probability,0,n_start_probability,0,start_probability.length);
  start_probability=n_start_probability;
  int[][] n_matrix=new int[ordinaryMax][ordinaryMax];
  for (int i=0; i < matrix.length; i++) {
    System.arraycopy(matrix[i],0,n_matrix[i],0,matrix.length);
  }
  matrix=n_matrix;
}
