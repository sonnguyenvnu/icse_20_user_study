protected boolean orthoProjection(){
  return zero(projection.m01) && zero(projection.m02) && zero(projection.m10) && zero(projection.m12) && zero(projection.m20) && zero(projection.m21) && zero(projection.m30) && zero(projection.m31) && zero(projection.m32) && same(projection.m33,1);
}
