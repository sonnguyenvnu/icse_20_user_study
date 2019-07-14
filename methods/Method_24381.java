protected void restoreCurveVertexSettings(){
  if (savedCurveDetail != curveDetail) {
    pg.curveDetail(savedCurveDetail);
  }
  if (savedCurveTightness != curveTightness) {
    pg.curveTightness(savedCurveTightness);
  }
}
