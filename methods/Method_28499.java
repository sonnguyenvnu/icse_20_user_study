@Override public void drawableHotspotChanged(float x,float y){
  super.drawableHotspotChanged(x,y);
  if (foreground != null) {
    foreground.setHotspot(x,y);
  }
}
