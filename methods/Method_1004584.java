@Override public void add(final long pX,final long pY){
  mLines[mIndex++]=pX;
  mLines[mIndex++]=pY;
  if (mIndex >= mLines.length) {
    innerFlush();
  }
}
