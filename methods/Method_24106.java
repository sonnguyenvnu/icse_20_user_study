@Override public void attribNormal(String name,float nx,float ny,float nz){
  VertexAttribute attrib=attribImpl(name,VertexAttribute.NORMAL,PGL.FLOAT,3);
  if (attrib != null)   attrib.set(nx,ny,nz);
}
