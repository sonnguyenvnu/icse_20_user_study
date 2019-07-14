protected void copyPolyShininess(int offset,int size){
  tessGeo.updatePolyShininessBuffer(offset,size);
  pgl.bindBuffer(PGL.ARRAY_BUFFER,bufPolyShininess.glId);
  tessGeo.polyShininessBuffer.position(offset);
  pgl.bufferSubData(PGL.ARRAY_BUFFER,offset * PGL.SIZEOF_FLOAT,size * PGL.SIZEOF_FLOAT,tessGeo.polyShininessBuffer);
  tessGeo.polyShininessBuffer.rewind();
  pgl.bindBuffer(PGL.ARRAY_BUFFER,0);
}
