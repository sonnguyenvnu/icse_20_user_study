public UFont scaled(double scale){
  if (scale == 1) {
    return this;
  }
  final float current=font.getSize2D();
  return withSize((float)(current * scale));
}
