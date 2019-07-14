private void parsePathQuadto(float cx,float cy,float x2,float y2){
  parsePathCode(QUADRATIC_VERTEX);
  parsePathVertex(cx,cy);
  parsePathVertex(x2,y2);
}
