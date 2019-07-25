public Point2D projection(Point2D pt,StringBounder stringBounder){
  if (getType() != ShapeType.FOLDER) {
    return pt;
  }
  final ClusterPosition clusterPosition=new ClusterPosition(minX,minY,minX + width,minY + height);
  if (clusterPosition.isPointJustUpper(pt)) {
    final Dimension2D dimName=((EntityImageDescription)image).getNameDimension(stringBounder);
    if (pt.getX() < minX + dimName.getWidth()) {
      return pt;
    }
    return new Point2D.Double(pt.getX(),pt.getY() + dimName.getHeight() + 4);
  }
  return pt;
}
