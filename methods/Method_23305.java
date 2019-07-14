protected int indexActual(char c){
  if (glyphCount == 0)   return -1;
  if (c < 128)   return ascii[c];
  return indexHunt(c,0,glyphCount - 1);
}
