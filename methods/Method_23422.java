/** 
 * Copies the matrix contents into a 6 entry float array. If target is null (or not the correct size), a new array will be created. Returned in the order  {@code} m00, m01, m02, m10, m11, m12}}.
 */
public float[] get(float[] target){
  if ((target == null) || (target.length != 6)) {
    target=new float[6];
  }
  target[0]=m00;
  target[1]=m01;
  target[2]=m02;
  target[3]=m10;
  target[4]=m11;
  target[5]=m12;
  return target;
}
