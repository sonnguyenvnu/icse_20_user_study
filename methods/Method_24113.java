protected void tessellate(int[] indices){
  tessellator.setInGeometry(inGeo);
  tessellator.setTessGeometry(tessGeo);
  tessellator.setFill(fill || textureImage != null);
  tessellator.setStroke(stroke);
  tessellator.setStrokeColor(strokeColor);
  tessellator.setStrokeWeight(strokeWeight);
  tessellator.setStrokeCap(strokeCap);
  tessellator.setStrokeJoin(strokeJoin);
  tessellator.setTexCache(texCache,textureImage);
  tessellator.setTransform(modelview);
  tessellator.set3D(is3D());
  if (stroke && defaultEdges)   inGeo.addTrianglesEdges();
  if (normalMode == NORMAL_MODE_AUTO)   inGeo.calcTrianglesNormals();
  tessellator.tessellateTriangles(indices);
}
