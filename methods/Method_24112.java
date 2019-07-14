protected void tessellate(int mode){
  tessellator.setInGeometry(inGeo);
  tessellator.setTessGeometry(tessGeo);
  tessellator.setFill(fill || textureImage != null);
  tessellator.setTexCache(texCache,textureImage);
  tessellator.setStroke(stroke);
  tessellator.setStrokeColor(strokeColor);
  tessellator.setStrokeWeight(strokeWeight);
  tessellator.setStrokeCap(strokeCap);
  tessellator.setStrokeJoin(strokeJoin);
  tessellator.setRenderer(this);
  tessellator.setTransform(modelview);
  tessellator.set3D(is3D());
  if (shape == POINTS) {
    tessellator.tessellatePoints();
  }
 else   if (shape == LINES) {
    tessellator.tessellateLines();
  }
 else   if (shape == LINE_STRIP) {
    tessellator.tessellateLineStrip();
  }
 else   if (shape == LINE_LOOP) {
    tessellator.tessellateLineLoop();
  }
 else   if (shape == TRIANGLE || shape == TRIANGLES) {
    if (stroke && defaultEdges)     inGeo.addTrianglesEdges();
    if (normalMode == NORMAL_MODE_AUTO)     inGeo.calcTrianglesNormals();
    tessellator.tessellateTriangles();
  }
 else   if (shape == TRIANGLE_FAN) {
    if (stroke && defaultEdges)     inGeo.addTriangleFanEdges();
    if (normalMode == NORMAL_MODE_AUTO)     inGeo.calcTriangleFanNormals();
    tessellator.tessellateTriangleFan();
  }
 else   if (shape == TRIANGLE_STRIP) {
    if (stroke && defaultEdges)     inGeo.addTriangleStripEdges();
    if (normalMode == NORMAL_MODE_AUTO)     inGeo.calcTriangleStripNormals();
    tessellator.tessellateTriangleStrip();
  }
 else   if (shape == QUAD || shape == QUADS) {
    if (stroke && defaultEdges)     inGeo.addQuadsEdges();
    if (normalMode == NORMAL_MODE_AUTO)     inGeo.calcQuadsNormals();
    tessellator.tessellateQuads();
  }
 else   if (shape == QUAD_STRIP) {
    if (stroke && defaultEdges)     inGeo.addQuadStripEdges();
    if (normalMode == NORMAL_MODE_AUTO)     inGeo.calcQuadStripNormals();
    tessellator.tessellateQuadStrip();
  }
 else   if (shape == POLYGON) {
    tessellator.tessellatePolygon(true,mode == CLOSE,normalMode == NORMAL_MODE_AUTO);
  }
}
