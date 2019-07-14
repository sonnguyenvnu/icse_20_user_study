protected void copyPolyNormals(int offset,int size){
  tessGeo.updatePolyNormalsBuffer(offset,size);
  pgl.bindBuffer(PGL.ARRAY_BUFFER,bufPolyNormal.glId);
  tessGeo.polyNormalsBuffer.position(3 * offset);
  pgl.bufferSubData(PGL.ARRAY_BUFFER,3 * offset * PGL.SIZEOF_FLOAT,3 * size * PGL.SIZEOF_FLOAT,tessGeo.polyNormalsBuffer);
  tessGeo.polyNormalsBuffer.rewind();
  pgl.bindBuffer(PGL.ARRAY_BUFFER,0);
}
