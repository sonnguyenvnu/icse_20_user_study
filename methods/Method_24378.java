protected void saveBezierVertexSettings(){
  savedBezierDetail=pg.bezierDetail;
  if (pg.bezierDetail != bezierDetail) {
    pg.bezierDetail(bezierDetail);
  }
}
