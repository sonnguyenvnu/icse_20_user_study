/** 
 * ( begin auto-generated from box.xml ) A box is an extruded rectangle. A box with equal dimension on all sides is a cube. ( end auto-generated )
 * @webref shape:3d_primitives
 * @param size dimension of the box in all dimensions (creates a cube)
 * @see PGraphics#sphere(float)
 */
public void box(float size){
  if (recorder != null)   recorder.box(size);
  g.box(size);
}
