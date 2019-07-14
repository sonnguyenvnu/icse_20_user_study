protected void bezierVertexImpl(float x2,float y2,float z2,float x3,float y3,float z3,float x4,float y4,float z4){
  inGeo.setMaterial(fillColor,strokeColor,strokeWeight,ambientColor,specularColor,emissiveColor,shininess);
  inGeo.setNormal(normalX,normalY,normalZ);
  inGeo.addBezierVertex(x2,y2,z2,x3,y3,z3,x4,y4,z4,vertexBreak());
}
