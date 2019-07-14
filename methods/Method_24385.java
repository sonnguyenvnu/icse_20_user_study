protected void setFirstStrokeVertex(int n,int vert){
  if (n == firstLineIndexCache && firstLineVertex == -1) {
    firstLineVertex=lastLineVertex=vert;
  }
  if (n == firstPointIndexCache && firstPointVertex == -1) {
    firstPointVertex=lastPointVertex=vert;
  }
}
