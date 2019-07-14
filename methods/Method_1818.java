/** 
 * Sets the target color space for decoding. When possible, the color space transformation will be performed at load time. This requires SDK version >= 26, otherwise it's a no-op.
 * @param colorSpace target color space for decoding.
 */
public ImageDecodeOptionsBuilder setColorSpace(ColorSpace colorSpace){
  mColorSpace=colorSpace;
  return this;
}
