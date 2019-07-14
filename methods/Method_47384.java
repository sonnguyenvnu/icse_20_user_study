private static int darker(int color){
  int a=Color.alpha(color);
  int r=Color.red(color);
  int g=Color.green(color);
  int b=Color.blue(color);
  return Color.argb(a,Math.max((int)(r * 0.6f),0),Math.max((int)(g * 0.6f),0),Math.max((int)(b * 0.6f),0));
}
