protected void curveVertexImpl(float x,float y,float z){
  curveVertexCheck(shape);
  inGeo.setMaterial(fillColor,strokeColor,strokeWeight,ambientColor,specularColor,emissiveColor,shininess);
  inGeo.setNormal(normalX,normalY,normalZ);
  inGeo.addCurveVertex(x,y,z,vertexBreak());
}
