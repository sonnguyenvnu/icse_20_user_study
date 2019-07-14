@Override public void vertex(float x,float y){
  curveVertexCount=0;
  if (vertexCount == vertices.length) {
    float temp[][]=new float[vertexCount << 1][VERTEX_FIELD_COUNT];
    System.arraycopy(vertices,0,temp,0,vertexCount);
    vertices=temp;
  }
  vertices[vertexCount][X]=x;
  vertices[vertexCount][Y]=y;
  vertexCount++;
switch (shape) {
case POINTS:
    point(x,y);
  break;
case LINES:
if ((vertexCount % 2) == 0) {
  line(vertices[vertexCount - 2][X],vertices[vertexCount - 2][Y],x,y);
}
break;
case TRIANGLES:
if ((vertexCount % 3) == 0) {
triangle(vertices[vertexCount - 3][X],vertices[vertexCount - 3][Y],vertices[vertexCount - 2][X],vertices[vertexCount - 2][Y],x,y);
}
break;
case TRIANGLE_STRIP:
if (vertexCount >= 3) {
triangle(vertices[vertexCount - 2][X],vertices[vertexCount - 2][Y],vertices[vertexCount - 1][X],vertices[vertexCount - 1][Y],vertices[vertexCount - 3][X],vertices[vertexCount - 3][Y]);
}
break;
case TRIANGLE_FAN:
if (vertexCount >= 3) {
triangle(vertices[0][X],vertices[0][Y],vertices[vertexCount - 2][X],vertices[vertexCount - 2][Y],x,y);
}
break;
case QUAD:
case QUADS:
if ((vertexCount % 4) == 0) {
quad(vertices[vertexCount - 4][X],vertices[vertexCount - 4][Y],vertices[vertexCount - 3][X],vertices[vertexCount - 3][Y],vertices[vertexCount - 2][X],vertices[vertexCount - 2][Y],x,y);
}
break;
case QUAD_STRIP:
if ((vertexCount >= 4) && ((vertexCount % 2) == 0)) {
quad(vertices[vertexCount - 4][X],vertices[vertexCount - 4][Y],vertices[vertexCount - 2][X],vertices[vertexCount - 2][Y],x,y,vertices[vertexCount - 3][X],vertices[vertexCount - 3][Y]);
}
break;
case POLYGON:
if (gpath == null) {
gpath=new GeneralPath();
gpath.moveTo(x,y);
}
 else if (breakShape) {
gpath.moveTo(x,y);
breakShape=false;
}
 else {
gpath.lineTo(x,y);
}
break;
}
}
