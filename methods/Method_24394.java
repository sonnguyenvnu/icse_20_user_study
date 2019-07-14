protected void copyPolySpecular(int offset,int size){
  tessGeo.updatePolySpecularBuffer(offset,size);
  pgl.bindBuffer(PGL.ARRAY_BUFFER,bufPolySpecular.glId);
  tessGeo.polySpecularBuffer.position(offset);
  pgl.bufferSubData(PGL.ARRAY_BUFFER,offset * PGL.SIZEOF_INT,size * PGL.SIZEOF_INT,tessGeo.polySpecularBuffer);
  tessGeo.polySpecularBuffer.rewind();
  pgl.bindBuffer(PGL.ARRAY_BUFFER,0);
}
