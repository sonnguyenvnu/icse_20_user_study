protected void copyPointVertices(int offset,int size){
  tessGeo.updatePointVerticesBuffer(offset,size);
  pgl.bindBuffer(PGL.ARRAY_BUFFER,bufPointVertex.glId);
  tessGeo.pointVerticesBuffer.position(4 * offset);
  pgl.bufferSubData(PGL.ARRAY_BUFFER,4 * offset * PGL.SIZEOF_FLOAT,4 * size * PGL.SIZEOF_FLOAT,tessGeo.pointVerticesBuffer);
  tessGeo.pointVerticesBuffer.rewind();
  pgl.bindBuffer(PGL.ARRAY_BUFFER,0);
}
