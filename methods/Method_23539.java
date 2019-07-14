private void parsePathVertex(float x,float y){
  if (vertexCount == vertices.length) {
    float[][] temp=new float[vertexCount << 1][2];
    System.arraycopy(vertices,0,temp,0,vertexCount);
    vertices=temp;
  }
  vertices[vertexCount][X]=x;
  vertices[vertexCount][Y]=y;
  vertexCount++;
}
