/** 
 * ( begin auto-generated from rotateY.xml ) Rotates a shape around the y-axis the amount specified by the <b>angle</b> parameter. Angles should be specified in radians (values from 0 to PI*2) or converted to radians with the <b>radians()</b> function. Objects are always rotated around their relative position to the origin and positive numbers rotate objects in a counterclockwise direction. Transformations apply to everything that happens after and subsequent calls to the function accumulates the effect. For example, calling <b>rotateY(PI/2)</b> and then <b>rotateY(PI/2)</b> is the same as <b>rotateY(PI)</b>. If <b>rotateY()</b> is called within the <b>draw()</b>, the transformation is reset when the loop begins again. This function requires using P3D as a third parameter to <b>size()</b> as shown in the examples above. ( end auto-generated )
 * @webref transform
 * @param angle angle of rotation specified in radians
 * @see PGraphics#popMatrix()
 * @see PGraphics#pushMatrix()
 * @see PGraphics#rotate(float)
 * @see PGraphics#rotateX(float)
 * @see PGraphics#rotateZ(float)
 * @see PGraphics#scale(float,float,float)
 * @see PGraphics#translate(float,float,float)
 */
public void rotateY(float angle){
  showMethodWarning("rotateY");
}
