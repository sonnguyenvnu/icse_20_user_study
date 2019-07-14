protected void copyPolyVertices(int offset,int size){
  tessGeo.updatePolyVerticesBuffer(offset,size);
  pgl.bindBuffer(PGL.ARRAY_BUFFER,bufPolyVertex.glId);
  tessGeo.polyVerticesBuffer.position(4 * offset);
  pgl.bufferSubData(PGL.ARRAY_BUFFER,4 * offset * PGL.SIZEOF_FLOAT,4 * size * PGL.SIZEOF_FLOAT,tessGeo.polyVerticesBuffer);
  tessGeo.polyVerticesBuffer.rewind();
  pgl.bindBuffer(PGL.ARRAY_BUFFER,0);
}
