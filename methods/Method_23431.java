/** 
 * Multiply a two element vector against this matrix. If out is null or not length four, a new float array will be returned. The values for vec and out can be the same (though that's less efficient).
 */
public float[] mult(float vec[],float out[]){
  if (out == null || out.length != 2) {
    out=new float[2];
  }
  if (vec == out) {
    float tx=m00 * vec[0] + m01 * vec[1] + m02;
    float ty=m10 * vec[0] + m11 * vec[1] + m12;
    out[0]=tx;
    out[1]=ty;
  }
 else {
    out[0]=m00 * vec[0] + m01 * vec[1] + m02;
    out[1]=m10 * vec[0] + m11 * vec[1] + m12;
  }
  return out;
}
