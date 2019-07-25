public boolean contains(ClosedArea other){
  if (isClosed() == false) {
    throw new IllegalStateException();
  }
  for (  Point2DInt point : other.points) {
    if (this.contains(point) == false) {
      return false;
    }
  }
  return true;
}
