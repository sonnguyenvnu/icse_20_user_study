protected void copyLineVertices(int offset,int size){
  tessGeo.updateLineVerticesBuffer(offset,size);
  pgl.bindBuffer(PGL.ARRAY_BUFFER,bufLineVertex.glId);
  tessGeo.lineVerticesBuffer.position(4 * offset);
  pgl.bufferSubData(PGL.ARRAY_BUFFER,4 * offset * PGL.SIZEOF_FLOAT,4 * size * PGL.SIZEOF_FLOAT,tessGeo.lineVerticesBuffer);
  tessGeo.lineVerticesBuffer.rewind();
  pgl.bindBuffer(PGL.ARRAY_BUFFER,0);
}
