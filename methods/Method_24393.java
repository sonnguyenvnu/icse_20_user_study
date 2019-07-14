protected void copyPolyAmbient(int offset,int size){
  tessGeo.updatePolyAmbientBuffer(offset,size);
  pgl.bindBuffer(PGL.ARRAY_BUFFER,bufPolyAmbient.glId);
  tessGeo.polyAmbientBuffer.position(offset);
  pgl.bufferSubData(PGL.ARRAY_BUFFER,offset * PGL.SIZEOF_INT,size * PGL.SIZEOF_INT,tessGeo.polyAmbientBuffer);
  tessGeo.polyAmbientBuffer.rewind();
  pgl.bindBuffer(PGL.ARRAY_BUFFER,0);
}
