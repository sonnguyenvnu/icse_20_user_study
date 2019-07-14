protected void updateLineBuffers(){
  createLineBuffers();
  int size=tessGeo.lineVertexCount;
  int sizef=size * PGL.SIZEOF_FLOAT;
  int sizei=size * PGL.SIZEOF_INT;
  tessGeo.updateLineVerticesBuffer();
  pgl.bindBuffer(PGL.ARRAY_BUFFER,bufLineVertex.glId);
  pgl.bufferData(PGL.ARRAY_BUFFER,4 * sizef,tessGeo.lineVerticesBuffer,PGL.STATIC_DRAW);
  tessGeo.updateLineColorsBuffer();
  pgl.bindBuffer(PGL.ARRAY_BUFFER,bufLineColor.glId);
  pgl.bufferData(PGL.ARRAY_BUFFER,sizei,tessGeo.lineColorsBuffer,PGL.STATIC_DRAW);
  tessGeo.updateLineDirectionsBuffer();
  pgl.bindBuffer(PGL.ARRAY_BUFFER,bufLineAttrib.glId);
  pgl.bufferData(PGL.ARRAY_BUFFER,4 * sizef,tessGeo.lineDirectionsBuffer,PGL.STATIC_DRAW);
  tessGeo.updateLineIndicesBuffer();
  pgl.bindBuffer(PGL.ELEMENT_ARRAY_BUFFER,bufLineIndex.glId);
  pgl.bufferData(PGL.ELEMENT_ARRAY_BUFFER,tessGeo.lineIndexCount * PGL.SIZEOF_INDEX,tessGeo.lineIndicesBuffer,PGL.STATIC_DRAW);
}
