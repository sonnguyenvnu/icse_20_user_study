public void preApply(float n00,float n01,float n02,float n10,float n11,float n12){
  float t0=m02;
  float t1=m12;
  n02+=t0 * n00 + t1 * n01;
  n12+=t0 * n10 + t1 * n11;
  m02=n02;
  m12=n12;
  t0=m00;
  t1=m10;
  m00=t0 * n00 + t1 * n01;
  m10=t0 * n10 + t1 * n11;
  t0=m01;
  t1=m11;
  m01=t0 * n00 + t1 * n01;
  m11=t0 * n10 + t1 * n11;
}
