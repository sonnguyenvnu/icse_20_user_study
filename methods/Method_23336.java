/** 
 * ( begin auto-generated from bezierVertex.xml ) Specifies vertex coordinates for Bezier curves. Each call to <b>bezierVertex()</b> defines the position of two control points and one anchor point of a Bezier curve, adding a new segment to a line or shape. The first time <b>bezierVertex()</b> is used within a <b>beginShape()</b> call, it must be prefaced with a call to <b>vertex()</b> to set the first anchor point. This function must be used between <b>beginShape()</b> and <b>endShape()</b> and only when there is no MODE parameter specified to <b>beginShape()</b>. Using the 3D version requires rendering with P3D (see the Environment reference for more information). ( end auto-generated )
 * @webref shape:vertex
 * @param x2 the x-coordinate of the 1st control point
 * @param y2 the y-coordinate of the 1st control point
 * @param z2 the z-coordinate of the 1st control point
 * @param x3 the x-coordinate of the 2nd control point
 * @param y3 the y-coordinate of the 2nd control point
 * @param z3 the z-coordinate of the 2nd control point
 * @param x4 the x-coordinate of the anchor point
 * @param y4 the y-coordinate of the anchor point
 * @param z4 the z-coordinate of the anchor point
 * @see PGraphics#curveVertex(float,float,float)
 * @see PGraphics#vertex(float,float,float,float,float)
 * @see PGraphics#quadraticVertex(float,float,float,float,float,float)
 * @see PGraphics#bezier(float,float,float,float,float,float,float,float,float,float,float,float)
 */
public void bezierVertex(float x2,float y2,float z2,float x3,float y3,float z3,float x4,float y4,float z4){
  bezierInitCheck();
  bezierVertexCheck();
  PMatrix3D draw=bezierDrawMatrix;
  float[] prev=vertices[vertexCount - 1];
  float x1=prev[X];
  float y1=prev[Y];
  float z1=prev[Z];
  float xplot1=draw.m10 * x1 + draw.m11 * x2 + draw.m12 * x3 + draw.m13 * x4;
  float xplot2=draw.m20 * x1 + draw.m21 * x2 + draw.m22 * x3 + draw.m23 * x4;
  float xplot3=draw.m30 * x1 + draw.m31 * x2 + draw.m32 * x3 + draw.m33 * x4;
  float yplot1=draw.m10 * y1 + draw.m11 * y2 + draw.m12 * y3 + draw.m13 * y4;
  float yplot2=draw.m20 * y1 + draw.m21 * y2 + draw.m22 * y3 + draw.m23 * y4;
  float yplot3=draw.m30 * y1 + draw.m31 * y2 + draw.m32 * y3 + draw.m33 * y4;
  float zplot1=draw.m10 * z1 + draw.m11 * z2 + draw.m12 * z3 + draw.m13 * z4;
  float zplot2=draw.m20 * z1 + draw.m21 * z2 + draw.m22 * z3 + draw.m23 * z4;
  float zplot3=draw.m30 * z1 + draw.m31 * z2 + draw.m32 * z3 + draw.m33 * z4;
  for (int j=0; j < bezierDetail; j++) {
    x1+=xplot1;
    xplot1+=xplot2;
    xplot2+=xplot3;
    y1+=yplot1;
    yplot1+=yplot2;
    yplot2+=yplot3;
    z1+=zplot1;
    zplot1+=zplot2;
    zplot2+=zplot3;
    vertex(x1,y1,z1);
  }
}
