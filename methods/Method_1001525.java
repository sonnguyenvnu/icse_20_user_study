private boolean inside(PairInt point){
  final int x=point.getX();
  final int y=point.getY();
  return x >= 0 && x < width && y >= 0 && y < height;
}
