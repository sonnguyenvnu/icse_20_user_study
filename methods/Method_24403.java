protected void copyPointAttributes(int offset,int size){
  tessGeo.updatePointOffsetsBuffer(offset,size);
  pgl.bindBuffer(PGL.ARRAY_BUFFER,bufPointAttrib.glId);
  tessGeo.pointOffsetsBuffer.position(2 * offset);
  pgl.bufferSubData(PGL.ARRAY_BUFFER,2 * offset * PGL.SIZEOF_FLOAT,2 * size * PGL.SIZEOF_FLOAT,tessGeo.pointOffsetsBuffer);
  tessGeo.pointOffsetsBuffer.rewind();
  pgl.bindBuffer(PGL.ARRAY_BUFFER,0);
}
