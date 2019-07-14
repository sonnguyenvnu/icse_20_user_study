@Override public ImageDecoder getGifDecoder(final Bitmap.Config bitmapConfig){
  return new ImageDecoder(){
    @Override public CloseableImage decode(    EncodedImage encodedImage,    int length,    QualityInfo qualityInfo,    ImageDecodeOptions options){
      return getAnimatedImageFactory().decodeGif(encodedImage,options,bitmapConfig);
    }
  }
;
}
