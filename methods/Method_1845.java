/** 
 * Returns true if all the image information has loaded, false otherwise.
 */
public static boolean isMetaDataAvailable(EncodedImage encodedImage){
  return encodedImage.mRotationAngle >= 0 && encodedImage.mWidth >= 0 && encodedImage.mHeight >= 0;
}
