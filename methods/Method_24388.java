protected void initLineBuffers(){
  int size=tessGeo.lineVertexCount;
  int sizef=size * PGL.SIZEOF_FLOAT;
  int sizei=size * PGL.SIZEOF_INT;
  tessGeo.updateLineVerticesBuffer();
  if (bufLineVertex == null)   bufLineVertex=new VertexBuffer(pg,PGL.ARRAY_BUFFER,4,PGL.SIZEOF_FLOAT);
  pgl.bindBuffer(PGL.ARRAY_BUFFER,bufLineVertex.glId);
  pgl.bufferData(PGL.ARRAY_BUFFER,4 * sizef,tessGeo.lineVerticesBuffer,glUsage);
  tessGeo.updateLineColorsBuffer();
  if (bufLineColor == null)   bufLineColor=new VertexBuffer(pg,PGL.ARRAY_BUFFER,1,PGL.SIZEOF_INT);
  pgl.bindBuffer(PGL.ARRAY_BUFFER,bufLineColor.glId);
  pgl.bufferData(PGL.ARRAY_BUFFER,sizei,tessGeo.lineColorsBuffer,glUsage);
  tessGeo.updateLineDirectionsBuffer();
  if (bufLineAttrib == null)   bufLineAttrib=new VertexBuffer(pg,PGL.ARRAY_BUFFER,4,PGL.SIZEOF_FLOAT);
  pgl.bindBuffer(PGL.ARRAY_BUFFER,bufLineAttrib.glId);
  pgl.bufferData(PGL.ARRAY_BUFFER,4 * sizef,tessGeo.lineDirectionsBuffer,glUsage);
  pgl.bindBuffer(PGL.ARRAY_BUFFER,0);
  tessGeo.updateLineIndicesBuffer();
  if (bufLineIndex == null)   bufLineIndex=new VertexBuffer(pg,PGL.ELEMENT_ARRAY_BUFFER,1,PGL.SIZEOF_INDEX,true);
  pgl.bindBuffer(PGL.ELEMENT_ARRAY_BUFFER,bufLineIndex.glId);
  pgl.bufferData(PGL.ELEMENT_ARRAY_BUFFER,tessGeo.lineIndexCount * PGL.SIZEOF_INDEX,tessGeo.lineIndicesBuffer,glUsage);
  pgl.bindBuffer(PGL.ELEMENT_ARRAY_BUFFER,0);
}
