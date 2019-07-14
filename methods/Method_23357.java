/** 
 * ( begin auto-generated from shearX.xml ) Shears a shape around the x-axis the amount specified by the <b>angle</b> parameter. Angles should be specified in radians (values from 0 to PI*2) or converted to radians with the <b>radians()</b> function. Objects are always sheared around their relative position to the origin and positive numbers shear objects in a clockwise direction. Transformations apply to everything that happens after and subsequent calls to the function accumulates the effect. For example, calling <b>shearX(PI/2)</b> and then <b>shearX(PI/2)</b> is the same as <b>shearX(PI)</b>. If <b>shearX()</b> is called within the <b>draw()</b>, the transformation is reset when the loop begins again. <br/> <br/> Technically, <b>shearX()</b> multiplies the current transformation matrix by a rotation matrix. This function can be further controlled by the <b>pushMatrix()</b> and <b>popMatrix()</b> functions. ( end auto-generated )
 * @webref transform
 * @param angle angle of shear specified in radians
 * @see PGraphics#popMatrix()
 * @see PGraphics#pushMatrix()
 * @see PGraphics#shearY(float)
 * @see PGraphics#scale(float,float,float)
 * @see PGraphics#translate(float,float,float)
 * @see PApplet#radians(float)
 */
public void shearX(float angle){
  showMissingWarning("shearX");
}
