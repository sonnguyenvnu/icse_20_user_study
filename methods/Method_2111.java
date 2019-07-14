@Override public void process(Bitmap bitmap){
  final int w=bitmap.getWidth();
  final int h=bitmap.getHeight();
  for (int x=0; x < w; x++) {
    for (int y=0; y < h; y++) {
      final int color=bitmap.getPixel(x,y);
      bitmap.setPixel(x,y,SlowGreyScalePostprocessor.getGreyColor(color));
    }
  }
}
