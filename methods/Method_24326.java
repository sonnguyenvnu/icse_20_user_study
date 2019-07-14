@Override public void bezierDetail(int detail){
  bezierDetail=detail;
  if (0 < inGeo.codeCount) {
    markForTessellation();
  }
}
