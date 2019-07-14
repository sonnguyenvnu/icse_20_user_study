protected void quadraticVertexImpl(float cx,float cy,float cz,float x3,float y3,float z3){
  inGeo.setMaterial(fillColor,strokeColor,strokeWeight,ambientColor,specularColor,emissiveColor,shininess);
  inGeo.setNormal(normalX,normalY,normalZ);
  inGeo.addQuadraticVertex(cx,cy,cz,x3,y3,z3,vertexBreak());
}
