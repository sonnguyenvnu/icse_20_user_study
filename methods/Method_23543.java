private void parsePathCurveto(float x1,float y1,float x2,float y2,float x3,float y3){
  parsePathCode(BEZIER_VERTEX);
  parsePathVertex(x1,y1);
  parsePathVertex(x2,y2);
  parsePathVertex(x3,y3);
}
