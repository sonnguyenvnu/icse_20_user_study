protected void vertexCheck(){
  if (vertexCount == vertices.length) {
    float temp[][]=new float[vertexCount << 1][VERTEX_FIELD_COUNT];
    System.arraycopy(vertices,0,temp,0,vertexCount);
    vertices=temp;
  }
}
