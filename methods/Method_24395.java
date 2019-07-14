protected void copyPolyEmissive(int offset,int size){
  tessGeo.updatePolyEmissiveBuffer(offset,size);
  pgl.bindBuffer(PGL.ARRAY_BUFFER,bufPolyEmissive.glId);
  tessGeo.polyEmissiveBuffer.position(offset);
  pgl.bufferSubData(PGL.ARRAY_BUFFER,offset * PGL.SIZEOF_INT,size * PGL.SIZEOF_INT,tessGeo.polyEmissiveBuffer);
  tessGeo.polyEmissiveBuffer.rewind();
  pgl.bindBuffer(PGL.ARRAY_BUFFER,0);
}
