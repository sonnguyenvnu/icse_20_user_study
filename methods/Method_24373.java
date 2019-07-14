protected void tessellateImpl(){
  tessGeo=root.tessGeo;
  firstPolyIndexCache=-1;
  lastPolyIndexCache=-1;
  firstLineIndexCache=-1;
  lastLineIndexCache=-1;
  firstPointIndexCache=-1;
  lastPointIndexCache=-1;
  if (family == GROUP) {
    if (polyAttribs == null) {
      polyAttribs=PGraphicsOpenGL.newAttributeMap();
      collectPolyAttribs();
    }
    for (int i=0; i < childCount; i++) {
      PShapeOpenGL child=(PShapeOpenGL)children[i];
      child.tessellateImpl();
    }
  }
 else {
    if (shapeCreated) {
      inGeo.clearEdges();
      tessellator.setInGeometry(inGeo);
      tessellator.setTessGeometry(tessGeo);
      tessellator.setFill(fill || image != null);
      tessellator.setTexCache(null,null);
      tessellator.setStroke(stroke);
      tessellator.setStrokeColor(strokeColor);
      tessellator.setStrokeWeight(strokeWeight);
      tessellator.setStrokeCap(strokeCap);
      tessellator.setStrokeJoin(strokeJoin);
      tessellator.setRenderer(pg);
      tessellator.setTransform(matrix);
      tessellator.set3D(is3D());
      if (family == GEOMETRY) {
        if (kind == POINTS) {
          tessellator.tessellatePoints();
        }
 else         if (kind == LINES) {
          tessellator.tessellateLines();
        }
 else         if (kind == LINE_STRIP) {
          tessellator.tessellateLineStrip();
        }
 else         if (kind == LINE_LOOP) {
          tessellator.tessellateLineLoop();
        }
 else         if (kind == TRIANGLE || kind == TRIANGLES) {
          if (stroke)           inGeo.addTrianglesEdges();
          if (normalMode == NORMAL_MODE_AUTO)           inGeo.calcTrianglesNormals();
          tessellator.tessellateTriangles();
        }
 else         if (kind == TRIANGLE_FAN) {
          if (stroke)           inGeo.addTriangleFanEdges();
          if (normalMode == NORMAL_MODE_AUTO)           inGeo.calcTriangleFanNormals();
          tessellator.tessellateTriangleFan();
        }
 else         if (kind == TRIANGLE_STRIP) {
          if (stroke)           inGeo.addTriangleStripEdges();
          if (normalMode == NORMAL_MODE_AUTO)           inGeo.calcTriangleStripNormals();
          tessellator.tessellateTriangleStrip();
        }
 else         if (kind == QUAD || kind == QUADS) {
          if (stroke)           inGeo.addQuadsEdges();
          if (normalMode == NORMAL_MODE_AUTO)           inGeo.calcQuadsNormals();
          tessellator.tessellateQuads();
        }
 else         if (kind == QUAD_STRIP) {
          if (stroke)           inGeo.addQuadStripEdges();
          if (normalMode == NORMAL_MODE_AUTO)           inGeo.calcQuadStripNormals();
          tessellator.tessellateQuadStrip();
        }
 else         if (kind == POLYGON) {
          boolean bez=inGeo.hasBezierVertex();
          boolean quad=inGeo.hasQuadraticVertex();
          boolean curv=inGeo.hasCurveVertex();
          if (bez || quad)           saveBezierVertexSettings();
          if (curv) {
            saveCurveVertexSettings();
            tessellator.resetCurveVertexCount();
          }
          tessellator.tessellatePolygon(solid,close,normalMode == NORMAL_MODE_AUTO);
          if (bez || quad)           restoreBezierVertexSettings();
          if (curv)           restoreCurveVertexSettings();
        }
      }
 else       if (family == PRIMITIVE) {
        inGeo.clear();
        if (kind == POINT) {
          tessellatePoint();
        }
 else         if (kind == LINE) {
          tessellateLine();
        }
 else         if (kind == TRIANGLE) {
          tessellateTriangle();
        }
 else         if (kind == QUAD) {
          tessellateQuad();
        }
 else         if (kind == RECT) {
          tessellateRect();
        }
 else         if (kind == ELLIPSE) {
          tessellateEllipse();
        }
 else         if (kind == ARC) {
          tessellateArc();
        }
 else         if (kind == BOX) {
          tessellateBox();
        }
 else         if (kind == SPHERE) {
          tessellateSphere();
        }
      }
 else       if (family == PATH) {
        inGeo.clear();
        tessellatePath();
      }
      if (image != null && parent != null) {
        ((PShapeOpenGL)parent).addTexture(image);
      }
      firstPolyIndexCache=tessellator.firstPolyIndexCache;
      lastPolyIndexCache=tessellator.lastPolyIndexCache;
      firstLineIndexCache=tessellator.firstLineIndexCache;
      lastLineIndexCache=tessellator.lastLineIndexCache;
      firstPointIndexCache=tessellator.firstPointIndexCache;
      lastPointIndexCache=tessellator.lastPointIndexCache;
    }
  }
  firstPolyVertex=lastPolyVertex=-1;
  firstLineVertex=lastLineVertex=-1;
  firstPointVertex=lastPointVertex=-1;
  tessellated=true;
}
