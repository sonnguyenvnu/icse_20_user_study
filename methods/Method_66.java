public int numWays(int n,int k){
  if (n <= 0) {
    return 0;
  }
  int sameColorCounts=0;
  int differentColorCounts=k;
  for (int i=2; i <= n; i++) {
    int temp=differentColorCounts;
    differentColorCounts=(sameColorCounts + differentColorCounts) * (k - 1);
    sameColorCounts=temp;
  }
  return sameColorCounts + differentColorCounts;
}
