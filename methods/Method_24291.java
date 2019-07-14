protected void setSpecularAttribute(int vboId,int size,int type,int stride,int offset){
  setAttributeVBO(specularLoc,vboId,size,type,true,stride,offset);
}
