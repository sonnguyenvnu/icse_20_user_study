/** 
 * ( begin auto-generated from ortho.xml ) Sets an orthographic projection and defines a parallel clipping volume. All objects with the same dimension appear the same size, regardless of whether they are near or far from the camera. The parameters to this function specify the clipping volume where left and right are the minimum and maximum x values, top and bottom are the minimum and maximum y values, and near and far are the minimum and maximum z values. If no parameters are given, the default is used: ortho(0, width, 0, height, -10, 10). ( end auto-generated )
 * @webref lights_camera:camera
 */
public void ortho(){
  showMissingWarning("ortho");
}
