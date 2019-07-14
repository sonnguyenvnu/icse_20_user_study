protected void updateGLProjection(){
  if (glProjection == null) {
    glProjection=new float[16];
  }
  glProjection[0]=projection.m00;
  glProjection[1]=projection.m10;
  glProjection[2]=projection.m20;
  glProjection[3]=projection.m30;
  glProjection[4]=projection.m01;
  glProjection[5]=projection.m11;
  glProjection[6]=projection.m21;
  glProjection[7]=projection.m31;
  glProjection[8]=projection.m02;
  glProjection[9]=projection.m12;
  glProjection[10]=projection.m22;
  glProjection[11]=projection.m32;
  glProjection[12]=projection.m03;
  glProjection[13]=projection.m13;
  glProjection[14]=projection.m23;
  glProjection[15]=projection.m33;
}
