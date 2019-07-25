public static Color darken(int r,int g,int b,double percent) throws IllegalArgumentException {
  return new Color(Math.max((int)(r * (1 - percent)),0),Math.max((int)(g * (1 - percent)),0),Math.max((int)(b * (1 - percent)),0));
}
