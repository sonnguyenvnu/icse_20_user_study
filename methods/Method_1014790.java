/** 
 * Rotates the direction and up vector of this camera by the given rotation matrix. The direction and up vector will not be orthogonalized.
 * @param transform The rotation matrix 
 */
public void rotate(final Matrix4 transform){
  direction.rot(transform);
  up.rot(transform);
}
