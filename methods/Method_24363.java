protected void setEmissiveImpl(int emissive){
  if (emissiveColor == emissive)   return;
  emissiveColor=emissive;
  Arrays.fill(inGeo.emissive,0,inGeo.vertexCount,PGL.javaToNativeARGB(emissiveColor));
  if (shapeCreated && tessellated && 0 < tessGeo.polyVertexCount) {
    if (is3D()) {
      Arrays.fill(tessGeo.polyEmissive,firstPolyVertex,lastPolyVertex + 1,PGL.javaToNativeARGB(emissiveColor));
      root.setModifiedPolyEmissive(firstPolyVertex,lastPolyVertex);
    }
 else     if (is2D()) {
      int last1=lastPolyVertex + 1;
      if (-1 < firstLineVertex)       last1=firstLineVertex;
      if (-1 < firstPointVertex)       last1=firstPointVertex;
      Arrays.fill(tessGeo.polyEmissive,firstPolyVertex,last1,PGL.javaToNativeARGB(emissiveColor));
      root.setModifiedPolyColors(firstPolyVertex,last1 - 1);
    }
  }
}
