/** 
 * Unsafe version of:  {@link #aiDecomposeMatrix DecomposeMatrix} 
 */
public static void naiDecomposeMatrix(long mat,long scaling,long rotation,long position){
  long __functionAddress=Functions.DecomposeMatrix;
  invokePPPPV(mat,scaling,rotation,position,__functionAddress);
}
