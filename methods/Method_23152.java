@Override protected void curveVertexSegment(float x1,float y1,float x2,float y2,float x3,float y3,float x4,float y4){
  curveCoordX[0]=x1;
  curveCoordY[0]=y1;
  curveCoordX[1]=x2;
  curveCoordY[1]=y2;
  curveCoordX[2]=x3;
  curveCoordY[2]=y3;
  curveCoordX[3]=x4;
  curveCoordY[3]=y4;
  curveToBezierMatrix.mult(curveCoordX,curveDrawX);
  curveToBezierMatrix.mult(curveCoordY,curveDrawY);
  if (gpath == null) {
    gpath=new GeneralPath();
    gpath.moveTo(curveDrawX[0],curveDrawY[0]);
  }
  gpath.curveTo(curveDrawX[1],curveDrawY[1],curveDrawX[2],curveDrawY[2],curveDrawX[3],curveDrawY[3]);
}
