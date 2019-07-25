/** 
 * Paints the specified polygon into the <tt>Graphics2D</tt> context. 
 */
public void render(Polygon polygon){
  int numberOfPoints=polygon.getNumberOfPoints();
  if (numberOfPoints > 0) {
    Color currentColor=_graphics.getColor();
    GeneralPath p=new GeneralPath(GeneralPath.WIND_EVEN_ODD,numberOfPoints);
    p.moveTo((float)polygon.getPoint(0).getX(),(float)polygon.getPoint(0).getY());
    for (int i=1; i < numberOfPoints; i++) {
      p.lineTo((float)polygon.getPoint(i).getX(),(float)polygon.getPoint(i).getY());
    }
    if (polygon.isClosed()) {
      p.closePath();
    }
    drawShape(p,polygon,currentColor);
  }
}
