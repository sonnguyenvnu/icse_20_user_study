public void translate(float tx,float ty){
  m02=tx * m00 + ty * m01 + m02;
  m12=tx * m10 + ty * m11 + m12;
}
