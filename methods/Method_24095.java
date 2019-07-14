protected void updateGLModelview(){
  if (glModelview == null) {
    glModelview=new float[16];
  }
  glModelview[0]=modelview.m00;
  glModelview[1]=modelview.m10;
  glModelview[2]=modelview.m20;
  glModelview[3]=modelview.m30;
  glModelview[4]=modelview.m01;
  glModelview[5]=modelview.m11;
  glModelview[6]=modelview.m21;
  glModelview[7]=modelview.m31;
  glModelview[8]=modelview.m02;
  glModelview[9]=modelview.m12;
  glModelview[10]=modelview.m22;
  glModelview[11]=modelview.m32;
  glModelview[12]=modelview.m03;
  glModelview[13]=modelview.m13;
  glModelview[14]=modelview.m23;
  glModelview[15]=modelview.m33;
}
