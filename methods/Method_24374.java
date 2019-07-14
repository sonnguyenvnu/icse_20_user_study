protected void tessellateTriangle(){
  float x1=0, y1=0;
  float x2=0, y2=0;
  float x3=0, y3=0;
  if (params.length == 6) {
    x1=params[0];
    y1=params[1];
    x2=params[2];
    y2=params[3];
    x3=params[4];
    y3=params[5];
  }
  inGeo.setMaterial(fillColor,strokeColor,strokeWeight,ambientColor,specularColor,emissiveColor,shininess);
  inGeo.setNormal(normalX,normalY,normalZ);
  inGeo.addTriangle(x1,y1,0,x2,y2,0,x3,y3,0,fill,stroke);
  tessellator.tessellateTriangles();
}
