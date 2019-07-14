private int halfTransparent(int argb){
  return Color.argb(Color.alpha(argb) / 2,Color.red(argb),Color.green(argb),Color.blue(argb));
}
