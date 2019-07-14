protected void copyPointColors(int offset,int size){
  tessGeo.updatePointColorsBuffer(offset,size);
  pgl.bindBuffer(PGL.ARRAY_BUFFER,bufPointColor.glId);
  tessGeo.pointColorsBuffer.position(offset);
  pgl.bufferSubData(PGL.ARRAY_BUFFER,offset * PGL.SIZEOF_INT,size * PGL.SIZEOF_INT,tessGeo.pointColorsBuffer);
  tessGeo.pointColorsBuffer.rewind();
  pgl.bindBuffer(PGL.ARRAY_BUFFER,0);
}
