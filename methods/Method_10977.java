private static int clamp(int n,int my,int child){
  if (my >= child || n < 0) {
    return 0;
  }
  if ((my + n) > child) {
    return child - my;
  }
  return n;
}
