protected void copyLineColors(int offset,int size){
  tessGeo.updateLineColorsBuffer(offset,size);
  pgl.bindBuffer(PGL.ARRAY_BUFFER,bufLineColor.glId);
  tessGeo.lineColorsBuffer.position(offset);
  pgl.bufferSubData(PGL.ARRAY_BUFFER,offset * PGL.SIZEOF_INT,size * PGL.SIZEOF_INT,tessGeo.lineColorsBuffer);
  tessGeo.lineColorsBuffer.rewind();
  pgl.bindBuffer(PGL.ARRAY_BUFFER,0);
}
