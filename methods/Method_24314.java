@Override public void attribPosition(String name,float x,float y,float z){
  VertexAttribute attrib=attribImpl(name,VertexAttribute.POSITION,PGL.FLOAT,3);
  if (attrib != null)   attrib.set(x,y,z);
}
