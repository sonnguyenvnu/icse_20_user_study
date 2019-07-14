protected float screenXImpl(float x,float y,float z,float w){
  float ox=projection.m00 * x + projection.m01 * y + projection.m02 * z + projection.m03 * w;
  float ow=projection.m30 * x + projection.m31 * y + projection.m32 * z + projection.m33 * w;
  if (nonZero(ow)) {
    ox/=ow;
  }
  float sx=width * (1 + ox) / 2.0f;
  return sx;
}
