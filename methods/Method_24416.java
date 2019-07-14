protected void rawLines(PGraphicsOpenGL g){
  PGraphics raw=g.getRaw();
  raw.colorMode(RGB);
  raw.noFill();
  raw.strokeCap(strokeCap);
  raw.strokeJoin(strokeJoin);
  raw.beginShape(LINES);
  float[] vertices=tessGeo.lineVertices;
  int[] color=tessGeo.lineColors;
  float[] attribs=tessGeo.lineDirections;
  short[] indices=tessGeo.lineIndices;
  IndexCache cache=tessGeo.lineIndexCache;
  for (int n=firstLineIndexCache; n <= lastLineIndexCache; n++) {
    int ioffset=cache.indexOffset[n];
    int icount=cache.indexCount[n];
    int voffset=cache.vertexOffset[n];
    for (int ln=ioffset / 6; ln < (ioffset + icount) / 6; ln++) {
      int i0=voffset + indices[6 * ln + 0];
      int i1=voffset + indices[6 * ln + 5];
      float sw0=2 * attribs[4 * i0 + 3];
      float sw1=2 * attribs[4 * i1 + 3];
      if (PGraphicsOpenGL.zero(sw0))       continue;
      float[] src0={0,0,0,0};
      float[] src1={0,0,0,0};
      float[] pt0={0,0,0,0};
      float[] pt1={0,0,0,0};
      int argb0=PGL.nativeToJavaARGB(color[i0]);
      int argb1=PGL.nativeToJavaARGB(color[i1]);
      PApplet.arrayCopy(vertices,4 * i0,src0,0,4);
      PApplet.arrayCopy(vertices,4 * i1,src1,0,4);
      g.modelview.mult(src0,pt0);
      g.modelview.mult(src1,pt1);
      if (raw.is3D()) {
        raw.strokeWeight(sw0);
        raw.stroke(argb0);
        raw.vertex(pt0[X],pt0[Y],pt0[Z]);
        raw.strokeWeight(sw1);
        raw.stroke(argb1);
        raw.vertex(pt1[X],pt1[Y],pt1[Z]);
      }
 else       if (raw.is2D()) {
        float sx0=g.screenXImpl(pt0[0],pt0[1],pt0[2],pt0[3]);
        float sy0=g.screenYImpl(pt0[0],pt0[1],pt0[2],pt0[3]);
        float sx1=g.screenXImpl(pt1[0],pt1[1],pt1[2],pt1[3]);
        float sy1=g.screenYImpl(pt1[0],pt1[1],pt1[2],pt1[3]);
        raw.strokeWeight(sw0);
        raw.stroke(argb0);
        raw.vertex(sx0,sy0);
        raw.strokeWeight(sw1);
        raw.stroke(argb1);
        raw.vertex(sx1,sy1);
      }
    }
  }
  raw.endShape();
}
