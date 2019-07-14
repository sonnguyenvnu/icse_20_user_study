public int climbStairs(int n){
  int a=1, b=1;
  while (--n > 0) {
    b+=a;
    a=b - a;
  }
  return b;
}
