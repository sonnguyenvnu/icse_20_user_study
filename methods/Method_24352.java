protected void setTintImpl(int tint){
  if (tintColor == tint)   return;
  tintColor=tint;
  if (image != null) {
    Arrays.fill(inGeo.colors,0,inGeo.vertexCount,PGL.javaToNativeARGB(tintColor));
    if (shapeCreated && tessellated && hasPolys) {
      if (is3D()) {
        Arrays.fill(tessGeo.polyColors,firstPolyVertex,lastPolyVertex + 1,PGL.javaToNativeARGB(tintColor));
        root.setModifiedPolyColors(firstPolyVertex,lastPolyVertex);
      }
 else       if (is2D()) {
        int last1=lastPolyVertex + 1;
        if (-1 < firstLineVertex)         last1=firstLineVertex;
        if (-1 < firstPointVertex)         last1=firstPointVertex;
        Arrays.fill(tessGeo.polyColors,firstPolyVertex,last1,PGL.javaToNativeARGB(tintColor));
        root.setModifiedPolyColors(firstPolyVertex,last1 - 1);
      }
    }
  }
}
