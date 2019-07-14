protected boolean startStrokedTex(int n){
  return image != null && (n == firstLineIndexCache || n == firstPointIndexCache);
}
