protected void setAttributeVBO(int loc,int vboId,int size,int type,boolean normalized,int stride,int offset){
  if (-1 < loc) {
    pgl.bindBuffer(PGL.ARRAY_BUFFER,vboId);
    pgl.vertexAttribPointer(loc,size,type,normalized,stride,offset);
  }
}
