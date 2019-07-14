/** 
 * Rotate the currently displaying media image.
 * @param rotationInDegrees The rotation in degrees
 */
public void rotatePicture(int rotationInDegrees){
  if (rotationInDegrees == -90 && imageView.getOrientation() == 0)   imageView.setOrientation(SubsamplingScaleImageView.ORIENTATION_270);
 else   imageView.setOrientation(Math.abs(imageView.getOrientation() + rotationInDegrees) % 360);
}
