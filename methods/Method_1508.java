/** 
 * Decodes image.
 * @param encodedImage input image (encoded bytes plus meta data)
 * @param length if image type supports decoding incomplete image then determines where the imagedata should be cut for decoding.
 * @param qualityInfo quality information for the image
 * @param options options that cange decode behavior
 */
@Override public CloseableImage decode(final EncodedImage encodedImage,final int length,final QualityInfo qualityInfo,final ImageDecodeOptions options){
  if (options.customImageDecoder != null) {
    return options.customImageDecoder.decode(encodedImage,length,qualityInfo,options);
  }
  ImageFormat imageFormat=encodedImage.getImageFormat();
  if (imageFormat == null || imageFormat == ImageFormat.UNKNOWN) {
    imageFormat=ImageFormatChecker.getImageFormat_WrapIOException(encodedImage.getInputStream());
    encodedImage.setImageFormat(imageFormat);
  }
  if (mCustomDecoders != null) {
    ImageDecoder decoder=mCustomDecoders.get(imageFormat);
    if (decoder != null) {
      return decoder.decode(encodedImage,length,qualityInfo,options);
    }
  }
  return mDefaultDecoder.decode(encodedImage,length,qualityInfo,options);
}
