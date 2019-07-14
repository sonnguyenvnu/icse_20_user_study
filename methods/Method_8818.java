private boolean isPOT(int x){
  return (x & (x - 1)) == 0;
}
