public Point2D uncompress(double x,double y,UnlinearCompression.Rounding rounding){
  return new Point2D.Double(compX.uncompress(x,rounding),compY.uncompress(y,rounding));
}
