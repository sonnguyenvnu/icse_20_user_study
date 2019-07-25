private int find(int[] A,int n){
  for (int i=0; i < A.length; i++) {
    if (A[i] == n) {
      return i;
    }
  }
  return -1;
}
