static public Rectangle2D convert(Positionable positionable){
  final Point2D position=positionable.getPosition();
  final Dimension2D size=positionable.getSize();
  return new Rectangle2D.Double(position.getX(),position.getY(),size.getWidth(),size.getHeight());
}
