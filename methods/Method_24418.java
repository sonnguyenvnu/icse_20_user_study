protected void rawPoints(PGraphicsOpenGL g){
  PGraphics raw=g.getRaw();
  raw.colorMode(RGB);
  raw.noFill();
  raw.strokeCap(strokeCap);
  raw.beginShape(POINTS);
  float[] vertices=tessGeo.pointVertices;
  int[] color=tessGeo.pointColors;
  float[] attribs=tessGeo.pointOffsets;
  short[] indices=tessGeo.pointIndices;
  IndexCache cache=tessGeo.pointIndexCache;
  for (int n=0; n < cache.size; n++) {
    int ioffset=cache.indexOffset[n];
    int icount=cache.indexCount[n];
    int voffset=cache.vertexOffset[n];
    int pt=ioffset;
    while (pt < (ioffset + icount) / 3) {
      float size=attribs[2 * pt + 2];
      float weight;
      int perim;
      if (0 < size) {
        weight=+size / 0.5f;
        perim=PApplet.min(PGraphicsOpenGL.MAX_POINT_ACCURACY,PApplet.max(PGraphicsOpenGL.MIN_POINT_ACCURACY,(int)(TWO_PI * weight / PGraphicsOpenGL.POINT_ACCURACY_FACTOR))) + 1;
      }
 else {
        weight=-size / 0.5f;
        perim=5;
      }
      int i0=voffset + indices[3 * pt];
      int argb0=PGL.nativeToJavaARGB(color[i0]);
      float[] pt0={0,0,0,0};
      float[] src0={0,0,0,0};
      PApplet.arrayCopy(vertices,4 * i0,src0,0,4);
      g.modelview.mult(src0,pt0);
      if (raw.is3D()) {
        raw.strokeWeight(weight);
        raw.stroke(argb0);
        raw.vertex(pt0[X],pt0[Y],pt0[Z]);
      }
 else       if (raw.is2D()) {
        float sx0=g.screenXImpl(pt0[0],pt0[1],pt0[2],pt0[3]);
        float sy0=g.screenYImpl(pt0[0],pt0[1],pt0[2],pt0[3]);
        raw.strokeWeight(weight);
        raw.stroke(argb0);
        raw.vertex(sx0,sy0);
      }
      pt+=perim;
    }
  }
  raw.endShape();
}
