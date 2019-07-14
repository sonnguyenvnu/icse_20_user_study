protected void saveCurveVertexSettings(){
  savedCurveDetail=pg.curveDetail;
  savedCurveTightness=pg.curveTightness;
  if (pg.curveDetail != curveDetail) {
    pg.curveDetail(curveDetail);
  }
  if (pg.curveTightness != curveTightness) {
    pg.curveTightness(curveTightness);
  }
}
