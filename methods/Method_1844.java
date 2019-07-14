/** 
 * Copy the meta data from another EncodedImage.
 * @param encodedImage the EncodedImage to copy the meta data from.
 */
public void copyMetaDataFrom(EncodedImage encodedImage){
  mImageFormat=encodedImage.getImageFormat();
  mWidth=encodedImage.getWidth();
  mHeight=encodedImage.getHeight();
  mRotationAngle=encodedImage.getRotationAngle();
  mExifOrientation=encodedImage.getExifOrientation();
  mSampleSize=encodedImage.getSampleSize();
  mStreamSize=encodedImage.getSize();
  mBytesRange=encodedImage.getBytesRange();
  mColorSpace=encodedImage.getColorSpace();
}
