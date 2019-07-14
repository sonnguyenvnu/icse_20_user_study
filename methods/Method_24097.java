protected void updateGLNormal(){
  if (glNormal == null) {
    glNormal=new float[9];
  }
  glNormal[0]=modelviewInv.m00;
  glNormal[1]=modelviewInv.m01;
  glNormal[2]=modelviewInv.m02;
  glNormal[3]=modelviewInv.m10;
  glNormal[4]=modelviewInv.m11;
  glNormal[5]=modelviewInv.m12;
  glNormal[6]=modelviewInv.m20;
  glNormal[7]=modelviewInv.m21;
  glNormal[8]=modelviewInv.m22;
}
