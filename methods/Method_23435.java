/** 
 * Copies the matrix contents into a 16 entry float array. If target is null (or not the correct size), a new array will be created.
 */
public float[] get(float[] target){
  if ((target == null) || (target.length != 16)) {
    target=new float[16];
  }
  target[0]=m00;
  target[1]=m01;
  target[2]=m02;
  target[3]=m03;
  target[4]=m10;
  target[5]=m11;
  target[6]=m12;
  target[7]=m13;
  target[8]=m20;
  target[9]=m21;
  target[10]=m22;
  target[11]=m23;
  target[12]=m30;
  target[13]=m31;
  target[14]=m32;
  target[15]=m33;
  return target;
}
