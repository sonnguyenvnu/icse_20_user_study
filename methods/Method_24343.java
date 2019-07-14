@Override public void setAttrib(String name,int index,float... values){
  if (openShape) {
    PGraphics.showWarning(INSIDE_BEGIN_END_ERROR,"setNormal()");
    return;
  }
  VertexAttribute attrib=attribImpl(name,VertexAttribute.OTHER,PGL.FLOAT,values.length);
  float[] array=inGeo.fattribs.get(name);
  for (int i=0; i < values.length; i++) {
    array[attrib.size * index + i]=values[i];
  }
  markForTessellation();
}
