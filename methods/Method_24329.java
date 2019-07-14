@Override public void curveDetail(int detail){
  curveDetail=detail;
  if (0 < inGeo.codeCount) {
    markForTessellation();
  }
}
