public static float[] LoadOrtho(float left,float right,float bottom,float top,float near,float far){
  float r_l=right - left;
  float t_b=top - bottom;
  float f_n=far - near;
  float tx=-(right + left) / (right - left);
  float ty=-(top + bottom) / (top - bottom);
  float tz=-(far + near) / (far - near);
  float out[]=new float[16];
  out[0]=2.0f / r_l;
  out[1]=0.0f;
  out[2]=0.0f;
  out[3]=0.0f;
  out[4]=0.0f;
  out[5]=2.0f / t_b;
  out[6]=0.0f;
  out[7]=0.0f;
  out[8]=0.0f;
  out[9]=0.0f;
  out[10]=-2.0f / f_n;
  out[11]=0.0f;
  out[12]=tx;
  out[13]=ty;
  out[14]=tz;
  out[15]=1.0f;
  return out;
}
