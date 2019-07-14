protected void setTexcoordAttribute(int vboId,int size,int type,int stride,int offset){
  setAttributeVBO(texCoordLoc,vboId,size,type,false,stride,offset);
}
