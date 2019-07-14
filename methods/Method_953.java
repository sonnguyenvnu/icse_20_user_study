@Override public ImageDecoder getWebPDecoder(final Bitmap.Config bitmapConfig){
  return new ImageDecoder(){
    @Override public CloseableImage decode(    EncodedImage encodedImage,    int length,    QualityInfo qualityInfo,    ImageDecodeOptions options){
      return getAnimatedImageFactory().decodeWebP(encodedImage,options,bitmapConfig);
    }
  }
;
}
