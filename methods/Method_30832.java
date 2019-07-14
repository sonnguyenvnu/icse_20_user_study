@Override protected void onResize(float width,float height){
  mPolygon.rewind();
  double radianPerSide=2 * Math.PI / mNumSides;
  float halfWidth=width / 2;
  float halfHeight=height / 2;
  mPolygon.moveTo(halfWidth,0);
  for (int i=1; i < mNumSides; ++i) {
    mPolygon.lineTo(halfWidth + (float)Math.sin(i * radianPerSide) * halfWidth,halfHeight - (float)Math.cos(i * radianPerSide) * halfHeight);
  }
  mPolygon.close();
}
