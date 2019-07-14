protected int indexHunt(int c,int start,int stop){
  int pivot=(start + stop) / 2;
  if (c == glyphs[pivot].value)   return pivot;
  if (start >= stop)   return -1;
  if (c < glyphs[pivot].value)   return indexHunt(c,start,pivot - 1);
  return indexHunt(c,pivot + 1,stop);
}
