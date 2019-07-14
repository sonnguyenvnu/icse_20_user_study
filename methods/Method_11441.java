@Override public void setSelectorPaintCoeff(float coeff){
  if (mItemsDimmedAlpha >= 100) {
    return;
  }
  LinearGradient shader;
  int w=getMeasuredWidth();
  int iw=getItemDimension();
  float p1=(1 - iw / (float)w) / 2;
  float p2=(1 + iw / (float)w) / 2;
  float z=mItemsDimmedAlpha * (1 - coeff);
  float c1f=z + 255 * coeff;
  if (mVisibleItems == 2) {
    int c1=Math.round(c1f) << 24;
    int c2=Math.round(z) << 24;
    int[] colors={c2,c1,0xff000000,0xff000000,c1,c2};
    float[] positions={0,p1,p1,p2,p2,1};
    shader=new LinearGradient(0,0,w,0,colors,positions,Shader.TileMode.CLAMP);
  }
 else {
    float p3=(1 - iw * 3 / (float)w) / 2;
    float p4=(1 + iw * 3 / (float)w) / 2;
    float s=255 * p3 / p1;
    float c3f=s * coeff;
    float c2f=z + c3f;
    int c1=Math.round(c1f) << 24;
    int c2=Math.round(c2f) << 24;
    int c3=Math.round(c3f) << 24;
    int[] colors={c2,c2,c2,c2,0xff000000,0xff000000,c2,c2,c2,c2};
    float[] positions={0,p3,p3,p1,p1,p2,p2,p4,p4,1};
    shader=new LinearGradient(0,0,w,0,colors,positions,Shader.TileMode.CLAMP);
  }
  mSelectorWheelPaint.setShader(shader);
  invalidate();
}
