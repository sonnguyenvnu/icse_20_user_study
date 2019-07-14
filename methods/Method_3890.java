private int clampApplyScroll(int tmpDt,int dt){
  final int before=tmpDt;
  tmpDt-=dt;
  if (before * tmpDt <= 0) {
    return 0;
  }
  return tmpDt;
}
