public Rectangle2D apply(Rectangle2D rect){
  return new Rectangle2D.Double(rect.getX() + dx,rect.getY() + dy,rect.getWidth(),rect.getHeight());
}
