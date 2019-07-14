/** 
 * @param target
 */
public float[] get(float[] target){
  if (target == null) {
    return new float[]{x,y,z};
  }
  if (target.length >= 2) {
    target[0]=x;
    target[1]=y;
  }
  if (target.length >= 3) {
    target[2]=z;
  }
  return target;
}
