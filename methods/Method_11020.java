public static int darken(int color,float factor){
  int a=Color.alpha(color);
  int r=Color.red(color);
  int g=Color.green(color);
  int b=Color.blue(color);
  return Color.argb(a,Math.max((int)(r * factor),0),Math.max((int)(g * factor),0),Math.max((int)(b * factor),0));
}
