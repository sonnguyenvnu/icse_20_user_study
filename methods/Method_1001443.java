/** 
 * Paints the specified oval into the current <tt>Graphics</tt> context.
 */
public void render(Oval oval){
  Color currentColor=_graphics.getColor();
  GraphPoint center=oval.getCenter();
  double width=oval.getWidth();
  double height=oval.getHeight();
  Ellipse2D ellipse=new Ellipse2D.Double(center.getX() - 0.5 * width,center.getY() - 0.5 * height,width,height);
  drawShape(ellipse,oval,currentColor);
}
