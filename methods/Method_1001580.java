public List<Point2D> intersection(){
  final List<Point2D> result=new ArrayList<Point2D>();
  final double delta=pow2(radius * line.getDr()) - pow2(line.getDiscriminant());
  if (delta < 0) {
    return result;
  }
  double x;
  double y;
  x=(line.getDiscriminant() * line.getDeltaY() + sgn(line.getDeltaY()) * line.getDeltaX() * Math.sqrt(delta)) / pow2(line.getDr());
  y=(-line.getDiscriminant() * line.getDeltaX() + Math.abs(line.getDeltaY()) * Math.sqrt(delta)) / pow2(line.getDr());
  result.add(new Point2D.Double(x,y));
  x=(line.getDiscriminant() * line.getDeltaY() - sgn(line.getDeltaY()) * line.getDeltaX() * Math.sqrt(delta)) / pow2(line.getDr());
  y=(-line.getDiscriminant() * line.getDeltaX() - Math.abs(line.getDeltaY()) * Math.sqrt(delta)) / pow2(line.getDr());
  result.add(new Point2D.Double(x,y));
  return result;
}
