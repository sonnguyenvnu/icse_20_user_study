@Override public void process(Bitmap bitmap){
  final int w=bitmap.getWidth();
  final int h=bitmap.getHeight();
  final int[] pixels=new int[w * h];
  bitmap.getPixels(pixels,0,w,0,0,w,h);
  for (int x=0; x < w; x++) {
    for (int y=0; y < h; y++) {
      final int offset=y * w + x;
      pixels[offset]=SlowGreyScalePostprocessor.getGreyColor(pixels[offset]);
    }
  }
  bitmap.setPixels(pixels,0,w,0,0,w,h);
}
