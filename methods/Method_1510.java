/** 
 * @param encodedImage input image (encoded bytes plus meta data)
 * @return a CloseableStaticBitmap
 */
public CloseableStaticBitmap decodeStaticImage(final EncodedImage encodedImage,ImageDecodeOptions options){
  CloseableReference<Bitmap> bitmapReference=mPlatformDecoder.decodeFromEncodedImageWithColorSpace(encodedImage,options.bitmapConfig,null,options.colorSpace);
  try {
    maybeApplyTransformation(options.bitmapTransformation,bitmapReference);
    return new CloseableStaticBitmap(bitmapReference,ImmutableQualityInfo.FULL_QUALITY,encodedImage.getRotationAngle(),encodedImage.getExifOrientation());
  }
  finally {
    bitmapReference.close();
  }
}
