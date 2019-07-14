protected static boolean similar(float[] A,float[] B){
  final float eta=1e-2f;
  for (int i=0; i < A.length; i++)   if (Math.abs(A[i] - B[i]) > eta)   return false;
  return true;
}
