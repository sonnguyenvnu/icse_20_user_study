private boolean intersect(int[] A,int[] B){
  for (int i=0, j=0; i < A.length && j < B.length; ) {
    if (A[i] == B[j])     return true;
 else     if (A[i] < B[j])     i++;
 else     j++;
  }
  return false;
}
