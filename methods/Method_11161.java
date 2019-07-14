private void updateUseColor(){
  int alpha=getAlpha();
  alpha+=alpha >> 7;
  final int baseAlpha=mBaseColor >>> 24;
  final int useAlpha=baseAlpha * alpha >> 8;
  mUseColor=(mBaseColor << 8 >>> 8) | (useAlpha << 24);
}
