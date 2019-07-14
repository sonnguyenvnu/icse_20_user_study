/** 
 * ( begin auto-generated from pointLight.xml ) Adds a point light. Lights need to be included in the <b>draw()</b> to remain persistent in a looping program. Placing them in the <b>setup()</b> of a looping program will cause them to only have an effect the first time through the loop. The affect of the <b>v1</b>, <b>v2</b>, and <b>v3</b> parameters is determined by the current color mode. The <b>x</b>, <b>y</b>, and <b>z</b> parameters set the position of the light. ( end auto-generated )
 * @webref lights_camera:lights
 * @usage web_application
 * @param v1 red or hue value (depending on current color mode)
 * @param v2 green or saturation value (depending on current color mode)
 * @param v3 blue or brightness value (depending on current color mode)
 * @param x x-coordinate of the light
 * @param y y-coordinate of the light
 * @param z z-coordinate of the light
 * @see PGraphics#lights()
 * @see PGraphics#directionalLight(float,float,float,float,float,float)
 * @see PGraphics#ambientLight(float,float,float,float,float,float)
 * @see PGraphics#spotLight(float,float,float,float,float,float,float,float,float,float,float)
 */
public void pointLight(float v1,float v2,float v3,float x,float y,float z){
  if (recorder != null)   recorder.pointLight(v1,v2,v3,x,y,z);
  g.pointLight(v1,v2,v3,x,y,z);
}
