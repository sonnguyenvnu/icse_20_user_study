private boolean isRightEdge(double x){
  final double width=this.getWidth();
  return x < width && x > width - contentPlaceHolder.snappedLeftInset();
}
