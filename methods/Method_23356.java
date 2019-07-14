/** 
 * ( begin auto-generated from scale.xml ) Increases or decreases the size of a shape by expanding and contracting vertices. Objects always scale from their relative origin to the coordinate system. Scale values are specified as decimal percentages. For example, the function call <b>scale(2.0)</b> increases the dimension of a shape by 200%. Transformations apply to everything that happens after and subsequent calls to the function multiply the effect. For example, calling <b>scale(2.0)</b> and then <b>scale(1.5)</b> is the same as <b>scale(3.0)</b>. If <b>scale()</b> is called within <b>draw()</b>, the transformation is reset when the loop begins again. Using this fuction with the <b>z</b> parameter requires using P3D as a parameter for <b>size()</b> as shown in the example above. This function can be further controlled by <b>pushMatrix()</b> and <b>popMatrix()</b>. ( end auto-generated )
 * @webref transform
 * @param s percentage to scale the object
 * @see PGraphics#pushMatrix()
 * @see PGraphics#popMatrix()
 * @see PGraphics#translate(float,float,float)
 * @see PGraphics#rotate(float)
 * @see PGraphics#rotateX(float)
 * @see PGraphics#rotateY(float)
 * @see PGraphics#rotateZ(float)
 */
public void scale(float s){
  showMissingWarning("scale");
}
