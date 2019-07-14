protected void copyPolyColors(int offset,int size){
  tessGeo.updatePolyColorsBuffer(offset,size);
  pgl.bindBuffer(PGL.ARRAY_BUFFER,bufPolyColor.glId);
  tessGeo.polyColorsBuffer.position(offset);
  pgl.bufferSubData(PGL.ARRAY_BUFFER,offset * PGL.SIZEOF_INT,size * PGL.SIZEOF_INT,tessGeo.polyColorsBuffer);
  tessGeo.polyColorsBuffer.rewind();
  pgl.bindBuffer(PGL.ARRAY_BUFFER,0);
}
