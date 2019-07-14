@Override public float getStrokeWeight(int index){
  if (family != GROUP) {
    return inGeo.strokeWeights[index];
  }
 else {
    return 0;
  }
}
