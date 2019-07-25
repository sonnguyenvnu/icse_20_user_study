private void flip(int[] A){
  for (int i=0; i < A.length; i++) {
    if (A[i] == 1) {
      A[i]=0;
    }
 else {
      A[i]=1;
    }
  }
}
