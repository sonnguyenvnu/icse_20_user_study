public static Color lighten(int r,int g,int b,double percent) throws IllegalArgumentException {
  int r2, g2, b2;
  r2=r + (int)((255 - r) * percent);
  g2=g + (int)((255 - g) * percent);
  b2=b + (int)((255 - b) * percent);
  return new Color(r2,g2,b2);
}
