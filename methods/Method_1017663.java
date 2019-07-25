/** 
 * Set the matrix to identity 
 */
public void reset(){
  MATRIX[MSCALE_X]=1;
  MATRIX[MSKEW_X]=0;
  MATRIX[MTRANS_X]=0;
  MATRIX[MSKEW_Y]=1;
  MATRIX[MSCALE_Y]=0;
  MATRIX[MTRANS_Y]=0;
  MATRIX[MPERSP_0]=0;
  MATRIX[MPERSP_1]=0;
  MATRIX[MPERSP_2]=1;
}
