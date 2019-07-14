/** 
 * Decodes gif into CloseableImage.
 * @param encodedImage input image (encoded bytes plus meta data)
 * @return a CloseableImage
 */
public CloseableImage decodeGif(final EncodedImage encodedImage,final int length,final QualityInfo qualityInfo,final ImageDecodeOptions options){
  if (encodedImage.getWidth() == EncodedImage.UNKNOWN_WIDTH || encodedImage.getHeight() == EncodedImage.UNKNOWN_HEIGHT) {
    throw new DecodeException("image width or height is incorrect",encodedImage);
  }
  if (!options.forceStaticImage && mAnimatedGifDecoder != null) {
    return mAnimatedGifDecoder.decode(encodedImage,length,qualityInfo,options);
  }
  return decodeStaticImage(encodedImage,options);
}
