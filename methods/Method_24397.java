protected void copyPolyAttrib(VertexAttribute attrib,int offset,int size){
  tessGeo.updateAttribBuffer(attrib.name,offset,size);
  pgl.bindBuffer(PGL.ARRAY_BUFFER,attrib.buf.glId);
  Buffer buf=tessGeo.polyAttribBuffers.get(attrib.name);
  buf.position(attrib.size * offset);
  pgl.bufferSubData(PGL.ARRAY_BUFFER,attrib.sizeInBytes(offset),attrib.sizeInBytes(size),buf);
  buf.rewind();
  pgl.bindBuffer(PGL.ARRAY_BUFFER,0);
}
