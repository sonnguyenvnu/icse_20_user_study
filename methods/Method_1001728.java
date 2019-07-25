static public boolean intersect(Positionable big,Positionable small){
  final Rectangle2D bigR=convert(big);
  final Rectangle2D smallR=convert(small);
  return bigR.intersects(smallR);
}
