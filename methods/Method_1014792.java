/** 
 * Moves the camera by the given amount on each axis.
 * @param x the displacement on the x-axis
 * @param y the displacement on the y-axis
 * @param z the displacement on the z-axis 
 */
public void translate(float x,float y,float z){
  position.add(x,y,z);
}
