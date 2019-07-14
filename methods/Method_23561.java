/** 
 * Make a new 2D unit vector from an angle
 * @param target the target vector (if null, a new vector will be created)
 * @return the PVector
 */
static public PVector fromAngle(float angle,PVector target){
  if (target == null) {
    target=new PVector((float)Math.cos(angle),(float)Math.sin(angle),0);
  }
 else {
    target.set((float)Math.cos(angle),(float)Math.sin(angle),0);
  }
  return target;
}
