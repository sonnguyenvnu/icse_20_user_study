protected VertexAttribute attribImpl(String name,int kind,int type,int size){
  if (4 < size) {
    PGraphics.showWarning("Vertex attributes cannot have more than 4 values");
    return null;
  }
  VertexAttribute attrib=polyAttribs.get(name);
  if (attrib == null) {
    attrib=new VertexAttribute(pg,name,kind,type,size);
    polyAttribs.put(name,attrib);
    inGeo.initAttrib(attrib);
  }
  if (attrib.kind != kind) {
    PGraphics.showWarning("The attribute kind cannot be changed after creation");
    return null;
  }
  if (attrib.type != type) {
    PGraphics.showWarning("The attribute type cannot be changed after creation");
    return null;
  }
  if (attrib.size != size) {
    PGraphics.showWarning("New value for vertex attribute has wrong number of values");
    return null;
  }
  return attrib;
}
