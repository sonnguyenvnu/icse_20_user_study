@Override public void curveTightness(float tightness){
  curveTightness=tightness;
  if (0 < inGeo.codeCount) {
    markForTessellation();
  }
}
