protected void lightPosition(int num,float x,float y,float z,boolean dir){
  lightPosition[4 * num + 0]=x * modelview.m00 + y * modelview.m01 + z * modelview.m02 + modelview.m03;
  lightPosition[4 * num + 1]=x * modelview.m10 + y * modelview.m11 + z * modelview.m12 + modelview.m13;
  lightPosition[4 * num + 2]=x * modelview.m20 + y * modelview.m21 + z * modelview.m22 + modelview.m23;
  lightPosition[4 * num + 3]=dir ? 0 : 1;
}
