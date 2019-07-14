public static long compute(long[] arrayA,long[] arrayB){
  final int m=arrayA.length;
  final int n=arrayB.length;
  if (m == 0 || n == 0)   return Long.MAX_VALUE / 3;
  long[][] d=new long[m + 1][n + 1];
  for (int j=0; j <= n; ++j) {
    d[0][j]=j;
  }
  for (int i=0; i <= m; ++i) {
    d[i][0]=i;
  }
  for (int i=1; i <= m; ++i) {
    long ci=arrayA[i - 1];
    for (int j=1; j <= n; ++j) {
      long cj=arrayB[j - 1];
      if (ci == cj) {
        d[i][j]=d[i - 1][j - 1];
      }
 else {
        d[i][j]=Math.min(d[i - 1][j - 1] + Math.abs(ci - cj),Math.min(d[i][j - 1] + cj,d[i - 1][j] + ci));
      }
    }
  }
  return d[m][n];
}
