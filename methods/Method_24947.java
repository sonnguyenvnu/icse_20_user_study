public void draw(Graphics2D g2d){
  AffineTransform trans=g2d.getTransform();
  g2d.translate(x,y);
  g2d.setColor(ColorScheme.getInstance().whitePaneColor);
  g2d.fillRect(-200 + lPolyX,-size,200 - lPolyX - width / 2,size + 1);
  g2d.fillRect(width / 2,-size,200 + rPolyX,size + 1);
  g2d.setColor(ColorScheme.getInstance().progressFillColor);
  AffineTransform tmp=g2d.getTransform();
  g2d.translate(-width / 2 - 5 + lPolyX,-size / 2);
  g2d.fillRect(0,-1,-lPolyX,2);
  g2d.fillPolygon(leftPoly);
  g2d.setTransform(tmp);
  g2d.translate(width / 2 + 5 + rPolyX,-size / 2);
  g2d.fillRect(-rPolyX,-1,rPolyX + 1,2);
  g2d.fillPolygon(rightPoly);
  g2d.setTransform(tmp);
  g2d.setTransform(trans);
}
