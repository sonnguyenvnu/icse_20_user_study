protected void copyLineAttributes(int offset,int size){
  tessGeo.updateLineDirectionsBuffer(offset,size);
  pgl.bindBuffer(PGL.ARRAY_BUFFER,bufLineAttrib.glId);
  tessGeo.lineDirectionsBuffer.position(4 * offset);
  pgl.bufferSubData(PGL.ARRAY_BUFFER,4 * offset * PGL.SIZEOF_FLOAT,4 * size * PGL.SIZEOF_FLOAT,tessGeo.lineDirectionsBuffer);
  tessGeo.lineDirectionsBuffer.rewind();
  pgl.bindBuffer(PGL.ARRAY_BUFFER,0);
}
