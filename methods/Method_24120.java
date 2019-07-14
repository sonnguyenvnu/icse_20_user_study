@Override public void quad(float x1,float y1,float x2,float y2,float x3,float y3,float x4,float y4){
  beginShape(QUADS);
  defaultEdges=false;
  normalMode=NORMAL_MODE_SHAPE;
  inGeo.setMaterial(fillColor,strokeColor,strokeWeight,ambientColor,specularColor,emissiveColor,shininess);
  inGeo.setNormal(normalX,normalY,normalZ);
  inGeo.addQuad(x1,y1,0,x2,y2,0,x3,y3,0,x4,y4,0,stroke);
  endShape();
}
