protected void flushPoints(){
  updatePointBuffers();
  PShader shader=getPointShader();
  shader.bind();
  IndexCache cache=tessGeo.pointIndexCache;
  for (int n=0; n < cache.size; n++) {
    int ioffset=cache.indexOffset[n];
    int icount=cache.indexCount[n];
    int voffset=cache.vertexOffset[n];
    shader.setVertexAttribute(bufPointVertex.glId,4,PGL.FLOAT,0,4 * voffset * PGL.SIZEOF_FLOAT);
    shader.setColorAttribute(bufPointColor.glId,4,PGL.UNSIGNED_BYTE,0,4 * voffset * PGL.SIZEOF_BYTE);
    shader.setPointAttribute(bufPointAttrib.glId,2,PGL.FLOAT,0,2 * voffset * PGL.SIZEOF_FLOAT);
    shader.draw(bufPointIndex.glId,icount,ioffset);
  }
  shader.unbind();
  unbindPointBuffers();
}
