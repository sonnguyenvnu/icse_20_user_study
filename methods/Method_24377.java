protected void tessellatePath(){
  if (vertices == null)   return;
  inGeo.setMaterial(fillColor,strokeColor,strokeWeight,ambientColor,specularColor,emissiveColor,shininess);
  if (vertexCodeCount == 0) {
    if (vertices[0].length == 2) {
      for (int i=0; i < vertexCount; i++) {
        inGeo.addVertex(vertices[i][X],vertices[i][Y],VERTEX,false);
      }
    }
 else {
      for (int i=0; i < vertexCount; i++) {
        inGeo.addVertex(vertices[i][X],vertices[i][Y],vertices[i][Z],VERTEX,false);
      }
    }
  }
 else {
    int idx=0;
    boolean brk=true;
    if (vertices[0].length == 2) {
      for (int j=0; j < vertexCodeCount; j++) {
switch (vertexCodes[j]) {
case VERTEX:
          inGeo.addVertex(vertices[idx][X],vertices[idx][Y],VERTEX,brk);
        brk=false;
      idx++;
    break;
case QUADRATIC_VERTEX:
  inGeo.addQuadraticVertex(vertices[idx + 0][X],vertices[idx + 0][Y],0,vertices[idx + 1][X],vertices[idx + 1][Y],0,brk);
brk=false;
idx+=2;
break;
case BEZIER_VERTEX:
inGeo.addBezierVertex(vertices[idx + 0][X],vertices[idx + 0][Y],0,vertices[idx + 1][X],vertices[idx + 1][Y],0,vertices[idx + 2][X],vertices[idx + 2][Y],0,brk);
brk=false;
idx+=3;
break;
case CURVE_VERTEX:
inGeo.addCurveVertex(vertices[idx][X],vertices[idx][Y],0,brk);
brk=false;
idx++;
break;
case BREAK:
brk=true;
}
}
}
 else {
for (int j=0; j < vertexCodeCount; j++) {
switch (vertexCodes[j]) {
case VERTEX:
inGeo.addVertex(vertices[idx][X],vertices[idx][Y],vertices[idx][Z],brk);
brk=false;
idx++;
break;
case QUADRATIC_VERTEX:
inGeo.addQuadraticVertex(vertices[idx + 0][X],vertices[idx + 0][Y],vertices[idx + 0][Z],vertices[idx + 1][X],vertices[idx + 1][Y],vertices[idx + 0][Z],brk);
brk=false;
idx+=2;
break;
case BEZIER_VERTEX:
inGeo.addBezierVertex(vertices[idx + 0][X],vertices[idx + 0][Y],vertices[idx + 0][Z],vertices[idx + 1][X],vertices[idx + 1][Y],vertices[idx + 1][Z],vertices[idx + 2][X],vertices[idx + 2][Y],vertices[idx + 2][Z],brk);
brk=false;
idx+=3;
break;
case CURVE_VERTEX:
inGeo.addCurveVertex(vertices[idx][X],vertices[idx][Y],vertices[idx][Z],brk);
brk=false;
idx++;
break;
case BREAK:
brk=true;
}
}
}
}
boolean bez=inGeo.hasBezierVertex();
boolean quad=inGeo.hasQuadraticVertex();
boolean curv=inGeo.hasCurveVertex();
if (bez || quad) saveBezierVertexSettings();
if (curv) {
saveCurveVertexSettings();
tessellator.resetCurveVertexCount();
}
tessellator.tessellatePolygon(true,close,true);
if (bez || quad) restoreBezierVertexSettings();
if (curv) restoreCurveVertexSettings();
}
