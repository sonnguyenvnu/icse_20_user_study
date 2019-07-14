/** 
 * Decode a webp animated image into a CloseableImage. <p> The image is decoded into a 'pinned' purgeable bitmap.
 * @param encodedImage input image (encoded bytes plus meta data)
 * @param options
 * @return a {@link CloseableImage}
 */
public CloseableImage decodeAnimatedWebp(final EncodedImage encodedImage,final int length,final QualityInfo qualityInfo,final ImageDecodeOptions options){
  return mAnimatedWebPDecoder.decode(encodedImage,length,qualityInfo,options);
}
