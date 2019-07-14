@Override protected void onResize(float width,float height){
  mStar.rewind();
  double radianPerPoint=Math.PI / mNumVertices;
  float halfWidth=width / 2;
  float halfHeight=height / 2;
  float halfInnerWidth=mInnerRadiusRatio * halfWidth;
  float halfInnerHeight=mInnerRadiusRatio * halfHeight;
  for (int i=0; i < mNumVertices; ++i) {
    if (i == 0) {
      mStar.moveTo(halfWidth,0);
    }
 else {
      mStar.lineTo(halfWidth + (float)Math.sin(2 * i * radianPerPoint) * halfWidth,halfHeight - (float)Math.cos(2 * i * radianPerPoint) * halfHeight);
    }
    mStar.lineTo(halfWidth + (float)Math.sin((2 * i + 1) * radianPerPoint) * halfInnerWidth,halfHeight - (float)Math.cos((2 * i + 1) * radianPerPoint) * halfInnerHeight);
  }
  mStar.close();
}
