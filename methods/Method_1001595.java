private Point2D corner(int i){
  double angle_deg=60 * i + 30;
  double angle_rad=Math.PI / 180 * angle_deg;
  return new Point2D.Double(size * Math.cos(angle_rad),size * Math.sin(angle_rad));
}
