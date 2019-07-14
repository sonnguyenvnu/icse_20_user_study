/** 
 * ( begin auto-generated from spotLight.xml ) Adds a spot light. Lights need to be included in the <b>draw()</b> to remain persistent in a looping program. Placing them in the <b>setup()</b> of a looping program will cause them to only have an effect the first time through the loop. The affect of the <b>v1</b>, <b>v2</b>, and <b>v3</b> parameters is determined by the current color mode. The <b>x</b>, <b>y</b>, and <b>z</b> parameters specify the position of the light and <b>nx</b>, <b>ny</b>, <b>nz</b> specify the direction or light. The <b>angle</b> parameter affects angle of the spotlight cone. ( end auto-generated )
 * @webref lights_camera:lights
 * @usage web_application
 * @param v1 red or hue value (depending on current color mode)
 * @param v2 green or saturation value (depending on current color mode)
 * @param v3 blue or brightness value (depending on current color mode)
 * @param x x-coordinate of the light
 * @param y y-coordinate of the light
 * @param z z-coordinate of the light
 * @param nx direction along the x axis
 * @param ny direction along the y axis
 * @param nz direction along the z axis
 * @param angle angle of the spotlight cone
 * @param concentration exponent determining the center bias of the cone
 * @see PGraphics#lights()
 * @see PGraphics#directionalLight(float,float,float,float,float,float)
 * @see PGraphics#pointLight(float,float,float,float,float,float)
 * @see PGraphics#ambientLight(float,float,float,float,float,float)
 */
public void spotLight(float v1,float v2,float v3,float x,float y,float z,float nx,float ny,float nz,float angle,float concentration){
  showMethodWarning("spotLight");
}
