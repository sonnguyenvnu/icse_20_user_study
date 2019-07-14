@Override public float modelZ(float x,float y,float z){
  float ax=modelview.m00 * x + modelview.m01 * y + modelview.m02 * z + modelview.m03;
  float ay=modelview.m10 * x + modelview.m11 * y + modelview.m12 * z + modelview.m13;
  float az=modelview.m20 * x + modelview.m21 * y + modelview.m22 * z + modelview.m23;
  float aw=modelview.m30 * x + modelview.m31 * y + modelview.m32 * z + modelview.m33;
  float oz=cameraInv.m20 * ax + cameraInv.m21 * ay + cameraInv.m22 * az + cameraInv.m23 * aw;
  float ow=cameraInv.m30 * ax + cameraInv.m31 * ay + cameraInv.m32 * az + cameraInv.m33 * aw;
  return nonZero(ow) ? oz / ow : oz;
}
