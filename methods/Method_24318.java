@Override public void setParams(float[] source){
  if (family != PRIMITIVE) {
    PGraphics.showWarning("Parameters can only be set to PRIMITIVE shapes");
    return;
  }
  super.setParams(source);
  markForTessellation();
  shapeCreated=true;
}
