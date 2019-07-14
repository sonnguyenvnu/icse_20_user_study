@Override public void bezierVertex(float x1,float y1,float x2,float y2,float x3,float y3){
  bezierVertexCheck();
  workPath.curveTo(x1,y1,x2,y2,x3,y3);
}
