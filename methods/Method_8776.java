public static float[] LoadGraphicsMatrix(Matrix matrix){
  float m[]=new float[16];
  float v[]=new float[9];
  matrix.getValues(v);
  m[0]=v[Matrix.MSCALE_X];
  m[1]=v[Matrix.MSKEW_X];
  m[2]=0.0f;
  m[3]=0.0f;
  m[4]=v[Matrix.MSKEW_Y];
  m[5]=v[Matrix.MSCALE_Y];
  m[6]=0.0f;
  m[7]=0.0f;
  m[8]=0.0f;
  m[9]=0.0f;
  m[10]=1.0f;
  m[11]=0.0f;
  m[12]=v[Matrix.MTRANS_X];
  m[13]=v[Matrix.MTRANS_Y];
  m[14]=0.0f;
  m[15]=1.0f;
  return m;
}
