protected void setEmissiveAttribute(int vboId,int size,int type,int stride,int offset){
  setAttributeVBO(emissiveLoc,vboId,size,type,true,stride,offset);
}
