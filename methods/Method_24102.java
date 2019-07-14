@Override public void beginShape(int kind){
  shape=kind;
  inGeo.clear();
  curveVertexCount=0;
  breakShape=false;
  defaultEdges=true;
  super.noTexture();
  normalMode=NORMAL_MODE_AUTO;
}
