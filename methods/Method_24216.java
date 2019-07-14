@Override protected void beginGL(){
  PMatrix3D proj=graphics.projection;
  PMatrix3D mdl=graphics.modelview;
  if (gl2x != null) {
    if (projMatrix == null) {
      projMatrix=new float[16];
    }
    gl2x.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
    projMatrix[0]=proj.m00;
    projMatrix[1]=proj.m10;
    projMatrix[2]=proj.m20;
    projMatrix[3]=proj.m30;
    projMatrix[4]=proj.m01;
    projMatrix[5]=proj.m11;
    projMatrix[6]=proj.m21;
    projMatrix[7]=proj.m31;
    projMatrix[8]=proj.m02;
    projMatrix[9]=proj.m12;
    projMatrix[10]=proj.m22;
    projMatrix[11]=proj.m32;
    projMatrix[12]=proj.m03;
    projMatrix[13]=proj.m13;
    projMatrix[14]=proj.m23;
    projMatrix[15]=proj.m33;
    gl2x.glLoadMatrixf(projMatrix,0);
    if (mvMatrix == null) {
      mvMatrix=new float[16];
    }
    gl2x.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
    mvMatrix[0]=mdl.m00;
    mvMatrix[1]=mdl.m10;
    mvMatrix[2]=mdl.m20;
    mvMatrix[3]=mdl.m30;
    mvMatrix[4]=mdl.m01;
    mvMatrix[5]=mdl.m11;
    mvMatrix[6]=mdl.m21;
    mvMatrix[7]=mdl.m31;
    mvMatrix[8]=mdl.m02;
    mvMatrix[9]=mdl.m12;
    mvMatrix[10]=mdl.m22;
    mvMatrix[11]=mdl.m32;
    mvMatrix[12]=mdl.m03;
    mvMatrix[13]=mdl.m13;
    mvMatrix[14]=mdl.m23;
    mvMatrix[15]=mdl.m33;
    gl2x.glLoadMatrixf(mvMatrix,0);
  }
}
