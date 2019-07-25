static Rectangle2D move(Rectangle2D rect,double dx,double dy){
  return new Rectangle2D.Double(rect.getX() + dx,rect.getY() + dy,rect.getWidth(),rect.getHeight());
}
