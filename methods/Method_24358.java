protected void setStrokeWeightImpl(float weight){
  if (PGraphicsOpenGL.same(strokeWeight,weight))   return;
  float oldWeight=strokeWeight;
  strokeWeight=weight;
  Arrays.fill(inGeo.strokeWeights,0,inGeo.vertexCount,strokeWeight);
  if (shapeCreated && tessellated && (hasLines || hasPoints)) {
    float resizeFactor=weight / oldWeight;
    if (hasLines) {
      if (is3D()) {
        for (int i=firstLineVertex; i <= lastLineVertex; i++) {
          tessGeo.lineDirections[4 * i + 3]*=resizeFactor;
        }
        root.setModifiedLineAttributes(firstLineVertex,lastLineVertex);
      }
 else       if (is2D()) {
        markForTessellation();
      }
    }
    if (hasPoints) {
      if (is3D()) {
        for (int i=firstPointVertex; i <= lastPointVertex; i++) {
          tessGeo.pointOffsets[2 * i + 0]*=resizeFactor;
          tessGeo.pointOffsets[2 * i + 1]*=resizeFactor;
        }
        root.setModifiedPointAttributes(firstPointVertex,lastPointVertex);
      }
 else       if (is2D()) {
        markForTessellation();
      }
    }
  }
}
