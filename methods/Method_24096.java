protected void updateGLProjmodelview(){
  if (glProjmodelview == null) {
    glProjmodelview=new float[16];
  }
  glProjmodelview[0]=projmodelview.m00;
  glProjmodelview[1]=projmodelview.m10;
  glProjmodelview[2]=projmodelview.m20;
  glProjmodelview[3]=projmodelview.m30;
  glProjmodelview[4]=projmodelview.m01;
  glProjmodelview[5]=projmodelview.m11;
  glProjmodelview[6]=projmodelview.m21;
  glProjmodelview[7]=projmodelview.m31;
  glProjmodelview[8]=projmodelview.m02;
  glProjmodelview[9]=projmodelview.m12;
  glProjmodelview[10]=projmodelview.m22;
  glProjmodelview[11]=projmodelview.m32;
  glProjmodelview[12]=projmodelview.m03;
  glProjmodelview[13]=projmodelview.m13;
  glProjmodelview[14]=projmodelview.m23;
  glProjmodelview[15]=projmodelview.m33;
}
