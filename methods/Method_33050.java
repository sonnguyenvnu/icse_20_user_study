private boolean isBottomEdge(double y){
  final double height=this.getHeight();
  return y < height && y > height - contentPlaceHolder.snappedLeftInset();
}
