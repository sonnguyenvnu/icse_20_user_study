public void vertex(float x,float y,float z){
  float vertex[]=vertices[vertexCount];
  vertex[X]=x;
  vertex[Y]=y;
  vertex[Z]=z;
  if (fill) {
    vertex[R]=fillR;
    vertex[G]=fillG;
    vertex[B]=fillB;
    vertex[A]=fillA;
  }
  if (stroke) {
    vertex[SR]=strokeR;
    vertex[SG]=strokeG;
    vertex[SB]=strokeB;
    vertex[SA]=strokeA;
    vertex[SW]=strokeWeight;
  }
  if (textureImage != null) {
    vertex[U]=textureU;
    vertex[V]=textureV;
  }
  vertexCount++;
  if ((shape == LINES) && (vertexCount == 2)) {
    writeLine(0,1);
    vertexCount=0;
  }
 else   if ((shape == TRIANGLES) && (vertexCount == 3)) {
    writeTriangle();
  }
}
