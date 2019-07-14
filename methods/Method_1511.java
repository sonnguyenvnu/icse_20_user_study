/** 
 * Decodes a partial jpeg.
 * @param encodedImage input image (encoded bytes plus meta data)
 * @param length amount of currently available data in bytes
 * @param qualityInfo quality info for the image
 * @return a CloseableStaticBitmap
 */
public CloseableStaticBitmap decodeJpeg(final EncodedImage encodedImage,int length,QualityInfo qualityInfo,ImageDecodeOptions options){
  CloseableReference<Bitmap> bitmapReference=mPlatformDecoder.decodeJPEGFromEncodedImageWithColorSpace(encodedImage,options.bitmapConfig,null,length,options.colorSpace);
  try {
    maybeApplyTransformation(options.bitmapTransformation,bitmapReference);
    return new CloseableStaticBitmap(bitmapReference,qualityInfo,encodedImage.getRotationAngle(),encodedImage.getExifOrientation());
  }
  finally {
    bitmapReference.close();
  }
}
