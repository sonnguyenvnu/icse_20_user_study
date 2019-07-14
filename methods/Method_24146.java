protected boolean nonOrthoProjection(){
  return nonZero(projection.m01) || nonZero(projection.m02) || nonZero(projection.m10) || nonZero(projection.m12) || nonZero(projection.m20) || nonZero(projection.m21) || nonZero(projection.m30) || nonZero(projection.m31) || nonZero(projection.m32) || diff(projection.m33,1);
}
