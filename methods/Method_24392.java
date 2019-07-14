protected void copyPolyTexCoords(int offset,int size){
  tessGeo.updatePolyTexCoordsBuffer(offset,size);
  pgl.bindBuffer(PGL.ARRAY_BUFFER,bufPolyTexcoord.glId);
  tessGeo.polyTexCoordsBuffer.position(2 * offset);
  pgl.bufferSubData(PGL.ARRAY_BUFFER,2 * offset * PGL.SIZEOF_FLOAT,2 * size * PGL.SIZEOF_FLOAT,tessGeo.polyTexCoordsBuffer);
  tessGeo.polyTexCoordsBuffer.rewind();
  pgl.bindBuffer(PGL.ARRAY_BUFFER,0);
}
