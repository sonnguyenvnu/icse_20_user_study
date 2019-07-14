public void setTestPoint(int testPoint){
  this.testPoint=testPoint > 4 || testPoint < 0 ? 4 : testPoint;
  dst=src.clone();
  resetPolyMatrix(this.testPoint);
  invalidate();
}
