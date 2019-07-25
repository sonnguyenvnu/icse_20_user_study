private int dup(int v){
  assert v >= 0 && v <= 15;
  return v * 16 + v;
}
