/** 
 * Sets the builder to be equivalent to the specified options.
 * @param options the options to copy from
 * @return this builder
 */
public ImageDecodeOptionsBuilder setFrom(ImageDecodeOptions options){
  mDecodePreviewFrame=options.decodePreviewFrame;
  mUseLastFrameForPreview=options.useLastFrameForPreview;
  mDecodeAllFrames=options.decodeAllFrames;
  mForceStaticImage=options.forceStaticImage;
  mBitmapConfig=options.bitmapConfig;
  mCustomImageDecoder=options.customImageDecoder;
  mBitmapTransformation=options.bitmapTransformation;
  mColorSpace=options.colorSpace;
  return this;
}
