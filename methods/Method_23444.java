/** 
 * Apply another matrix to the left of this one.
 */
public void preApply(float n00,float n01,float n02,float n03,float n10,float n11,float n12,float n13,float n20,float n21,float n22,float n23,float n30,float n31,float n32,float n33){
  float r00=n00 * m00 + n01 * m10 + n02 * m20 + n03 * m30;
  float r01=n00 * m01 + n01 * m11 + n02 * m21 + n03 * m31;
  float r02=n00 * m02 + n01 * m12 + n02 * m22 + n03 * m32;
  float r03=n00 * m03 + n01 * m13 + n02 * m23 + n03 * m33;
  float r10=n10 * m00 + n11 * m10 + n12 * m20 + n13 * m30;
  float r11=n10 * m01 + n11 * m11 + n12 * m21 + n13 * m31;
  float r12=n10 * m02 + n11 * m12 + n12 * m22 + n13 * m32;
  float r13=n10 * m03 + n11 * m13 + n12 * m23 + n13 * m33;
  float r20=n20 * m00 + n21 * m10 + n22 * m20 + n23 * m30;
  float r21=n20 * m01 + n21 * m11 + n22 * m21 + n23 * m31;
  float r22=n20 * m02 + n21 * m12 + n22 * m22 + n23 * m32;
  float r23=n20 * m03 + n21 * m13 + n22 * m23 + n23 * m33;
  float r30=n30 * m00 + n31 * m10 + n32 * m20 + n33 * m30;
  float r31=n30 * m01 + n31 * m11 + n32 * m21 + n33 * m31;
  float r32=n30 * m02 + n31 * m12 + n32 * m22 + n33 * m32;
  float r33=n30 * m03 + n31 * m13 + n32 * m23 + n33 * m33;
  m00=r00;
  m01=r01;
  m02=r02;
  m03=r03;
  m10=r10;
  m11=r11;
  m12=r12;
  m13=r13;
  m20=r20;
  m21=r21;
  m22=r22;
  m23=r23;
  m30=r30;
  m31=r31;
  m32=r32;
  m33=r33;
}
