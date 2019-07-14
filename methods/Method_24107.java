@Override public void attribColor(String name,int color){
  VertexAttribute attrib=attribImpl(name,VertexAttribute.COLOR,PGL.INT,1);
  if (attrib != null)   attrib.set(new int[]{color});
}
