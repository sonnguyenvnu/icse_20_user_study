@Override protected void arcImpl(float x,float y,float w,float h,float start,float stop,int mode){
  beginShape(TRIANGLE_FAN);
  defaultEdges=false;
  normalMode=NORMAL_MODE_SHAPE;
  inGeo.setMaterial(fillColor,strokeColor,strokeWeight,ambientColor,specularColor,emissiveColor,shininess);
  inGeo.setNormal(normalX,normalY,normalZ);
  inGeo.addArc(x,y,w,h,start,stop,fill,stroke,mode);
  endShape();
}
