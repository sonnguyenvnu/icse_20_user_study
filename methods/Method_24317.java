@Override public void endShape(int mode){
  super.endShape(mode);
  inGeo.trim();
  close=mode == CLOSE;
  markForTessellation();
  shapeCreated=true;
}
