@Override public void paint(Graphics2D g,double xOffset,double yOffset,int markerSize){
  g.setStroke(stroke);
  double halfSize=(double)markerSize / 2;
  Path2D.Double path=new Path2D.Double();
  path.moveTo(xOffset + halfSize,yOffset);
  path.lineTo(xOffset - halfSize,yOffset);
  path.moveTo(xOffset,yOffset + halfSize);
  path.lineTo(xOffset,yOffset - halfSize);
  g.draw(path);
}
