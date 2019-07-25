public Matrix4 scl(float x,float y,float z){
  val[M00]*=x;
  val[M11]*=y;
  val[M22]*=z;
  return this;
}
