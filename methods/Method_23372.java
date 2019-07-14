/** 
 * ( begin auto-generated from modelX.xml ) Returns the three-dimensional X, Y, Z position in model space. This returns the X value for a given coordinate based on the current set of transformations (scale, rotate, translate, etc.) The X value can be used to place an object in space relative to the location of the original point once the transformations are no longer in use. <br/> <br/> In the example, the <b>modelX()</b>, <b>modelY()</b>, and <b>modelZ()</b> functions record the location of a box in space after being placed using a series of translate and rotate commands. After popMatrix() is called, those transformations no longer apply, but the (x, y, z) coordinate returned by the model functions is used to place another box in the same location. ( end auto-generated )
 * @webref lights_camera:coordinates
 * @param x 3D x-coordinate to be mapped
 * @param y 3D y-coordinate to be mapped
 * @param z 3D z-coordinate to be mapped
 * @see PGraphics#modelY(float,float,float)
 * @see PGraphics#modelZ(float,float,float)
 */
public float modelX(float x,float y,float z){
  showMissingWarning("modelX");
  return 0;
}
