/** 
 * ( begin auto-generated from camera.xml ) Sets the position of the camera through setting the eye position, the center of the scene, and which axis is facing upward. Moving the eye position and the direction it is pointing (the center of the scene) allows the images to be seen from different angles. The version without any parameters sets the camera to the default position, pointing to the center of the display window with the Y axis as up. The default values are <b>camera(width/2.0, height/2.0, (height/2.0) / tan(PI*30.0 / 180.0), width/2.0, height/2.0, 0, 0, 1, 0)</b>. This function is similar to <b>gluLookAt()</b> in OpenGL, but it first clears the current camera settings. ( end auto-generated )
 * @webref lights_camera:camera
 * @see PGraphics#beginCamera()
 * @see PGraphics#endCamera()
 * @see PGraphics#frustum(float,float,float,float,float,float)
 */
public void camera(){
  showMissingWarning("camera");
}
