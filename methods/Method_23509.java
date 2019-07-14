/** 
 * @nowebref
 */
public void setStroke(boolean stroke){
  if (openShape) {
    PGraphics.showWarning(INSIDE_BEGIN_END_ERROR,"setStroke()");
    return;
  }
  this.stroke=stroke;
}
