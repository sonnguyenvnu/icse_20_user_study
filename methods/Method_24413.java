private void inGeoToVertices(){
  vertexCount=0;
  vertexCodeCount=0;
  if (inGeo.codeCount == 0) {
    for (int i=0; i < inGeo.vertexCount; i++) {
      int index=3 * i;
      float x=inGeo.vertices[index++];
      float y=inGeo.vertices[index];
      super.vertex(x,y);
    }
  }
 else {
    int v;
    float x, y;
    float cx, cy;
    float x2, y2, x3, y3, x4, y4;
    int idx=0;
    boolean insideContour=false;
    for (int j=0; j < inGeo.codeCount; j++) {
switch (inGeo.codes[j]) {
case VERTEX:
        v=3 * idx;
      x=inGeo.vertices[v++];
    y=inGeo.vertices[v];
  super.vertex(x,y);
idx++;
break;
case QUADRATIC_VERTEX:
v=3 * idx;
cx=inGeo.vertices[v++];
cy=inGeo.vertices[v];
v=3 * (idx + 1);
x3=inGeo.vertices[v++];
y3=inGeo.vertices[v];
super.quadraticVertex(cx,cy,x3,y3);
idx+=2;
break;
case BEZIER_VERTEX:
v=3 * idx;
x2=inGeo.vertices[v++];
y2=inGeo.vertices[v];
v=3 * (idx + 1);
x3=inGeo.vertices[v++];
y3=inGeo.vertices[v];
v=3 * (idx + 2);
x4=inGeo.vertices[v++];
y4=inGeo.vertices[v];
super.bezierVertex(x2,y2,x3,y3,x4,y4);
idx+=3;
break;
case CURVE_VERTEX:
v=3 * idx;
x=inGeo.vertices[v++];
y=inGeo.vertices[v];
super.curveVertex(x,y);
idx++;
break;
case BREAK:
if (insideContour) {
super.endContourImpl();
}
super.beginContourImpl();
insideContour=true;
}
}
if (insideContour) {
super.endContourImpl();
}
}
}
