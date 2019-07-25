/** 
 * Paints the specified rectangle into the current <tt>Graphics</tt> context.
 */
public void render(Rectangle rectangle){
  Color currentColor=_graphics.getColor();
  GraphPoint center=rectangle.getCenter();
  double width=rectangle.getWidth();
  double height=rectangle.getHeight();
  Rectangle2D rect=new Rectangle2D.Double(center.getX() - 0.5 * width,center.getY() - 0.5 * height,width,height);
  drawShape(rect,rectangle,currentColor);
}
