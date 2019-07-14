/** 
 * Unsafe version of:  {@link #aiCreateQuaternionFromMatrix CreateQuaternionFromMatrix} 
 */
public static void naiCreateQuaternionFromMatrix(long quat,long mat){
  long __functionAddress=Functions.CreateQuaternionFromMatrix;
  invokePPV(quat,mat,__functionAddress);
}
