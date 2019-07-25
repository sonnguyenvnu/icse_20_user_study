@Override public Resource<BitmapPaletteWrapper> transcode(Resource<Bitmap> bitmapResource){
  Bitmap bitmap=bitmapResource.get();
  BitmapPaletteWrapper bitmapPaletteWrapper=new BitmapPaletteWrapper(bitmap,PhonographColorUtil.generatePalette(bitmap));
  return new BitmapPaletteResource(bitmapPaletteWrapper,bitmapPool);
}
