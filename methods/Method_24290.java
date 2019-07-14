protected void setAmbientAttribute(int vboId,int size,int type,int stride,int offset){
  setAttributeVBO(ambientLoc,vboId,size,type,true,stride,offset);
}
