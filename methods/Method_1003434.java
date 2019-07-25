private int read(int pos){
  int p=next[pos];
  if (p == 0) {
    int n=data[pos] & 255;
    if (n < 2 || n >= MAX_SPLIT) {
      return pos + 1;
    }
    int start=pos++;
    for (int i=0; i < n; i++) {
      pos=read(pos);
    }
    next[start]=p=pos;
  }
  return p;
}
