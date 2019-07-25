@Override public void flush(){
  if (getSize() >= 4) {
    mCanvas.drawLines(getLines(),0,getSize(),mPaint);
  }
}
