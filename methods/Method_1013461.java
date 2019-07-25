public static final int nbits(int tmp){
  int nb=0;
  while (tmp != 0 && tmp != -1) {
    nb++;
    tmp>>=1;
  }
  return nb + 1;
}
