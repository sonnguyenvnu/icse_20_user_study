protected void renderPoints(PGraphicsOpenGL g){
  PShader shader=g.getPointShader();
  shader.bind();
  IndexCache cache=tessGeo.pointIndexCache;
  for (int n=firstPointIndexCache; n <= lastPointIndexCache; n++) {
    int ioffset=cache.indexOffset[n];
    int icount=cache.indexCount[n];
    int voffset=cache.vertexOffset[n];
    shader.setVertexAttribute(root.bufPointVertex.glId,4,PGL.FLOAT,0,4 * voffset * PGL.SIZEOF_FLOAT);
    shader.setColorAttribute(root.bufPointColor.glId,4,PGL.UNSIGNED_BYTE,0,4 * voffset * PGL.SIZEOF_BYTE);
    shader.setPointAttribute(root.bufPointAttrib.glId,2,PGL.FLOAT,0,2 * voffset * PGL.SIZEOF_FLOAT);
    shader.draw(root.bufPointIndex.glId,icount,ioffset);
  }
  shader.unbind();
}
