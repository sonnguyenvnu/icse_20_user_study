protected void renderLines(PGraphicsOpenGL g){
  PShader shader=g.getLineShader();
  shader.bind();
  IndexCache cache=tessGeo.lineIndexCache;
  for (int n=firstLineIndexCache; n <= lastLineIndexCache; n++) {
    int ioffset=cache.indexOffset[n];
    int icount=cache.indexCount[n];
    int voffset=cache.vertexOffset[n];
    shader.setVertexAttribute(root.bufLineVertex.glId,4,PGL.FLOAT,0,4 * voffset * PGL.SIZEOF_FLOAT);
    shader.setColorAttribute(root.bufLineColor.glId,4,PGL.UNSIGNED_BYTE,0,4 * voffset * PGL.SIZEOF_BYTE);
    shader.setLineAttribute(root.bufLineAttrib.glId,4,PGL.FLOAT,0,4 * voffset * PGL.SIZEOF_FLOAT);
    shader.draw(root.bufLineIndex.glId,icount,ioffset);
  }
  shader.unbind();
}
