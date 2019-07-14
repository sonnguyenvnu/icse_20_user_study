public static int[] appendInt(int[] cur,int val){
  if (cur == null) {
    return new int[]{val};
  }
  final int N=cur.length;
  for (int i=0; i < N; i++) {
    if (cur[i] == val) {
      return cur;
    }
  }
  int[] ret=new int[N + 1];
  System.arraycopy(cur,0,ret,0,N);
  ret[N]=val;
  return ret;
}
