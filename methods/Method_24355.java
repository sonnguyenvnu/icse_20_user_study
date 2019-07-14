protected void setStrokeImpl(int stroke){
  if (strokeColor == stroke)   return;
  strokeColor=stroke;
  Arrays.fill(inGeo.strokeColors,0,inGeo.vertexCount,PGL.javaToNativeARGB(strokeColor));
  if (shapeCreated && tessellated && (hasLines || hasPoints)) {
    if (hasLines) {
      if (is3D()) {
        Arrays.fill(tessGeo.lineColors,firstLineVertex,lastLineVertex + 1,PGL.javaToNativeARGB(strokeColor));
        root.setModifiedLineColors(firstLineVertex,lastLineVertex);
      }
 else       if (is2D()) {
        Arrays.fill(tessGeo.polyColors,firstLineVertex,lastLineVertex + 1,PGL.javaToNativeARGB(strokeColor));
        root.setModifiedPolyColors(firstLineVertex,lastLineVertex);
      }
    }
    if (hasPoints) {
      if (is3D()) {
        Arrays.fill(tessGeo.pointColors,firstPointVertex,lastPointVertex + 1,PGL.javaToNativeARGB(strokeColor));
        root.setModifiedPointColors(firstPointVertex,lastPointVertex);
      }
 else       if (is2D()) {
        Arrays.fill(tessGeo.polyColors,firstPointVertex,lastPointVertex + 1,PGL.javaToNativeARGB(strokeColor));
        root.setModifiedPolyColors(firstPointVertex,lastPointVertex);
      }
    }
  }
}
