/** 
 * Declare the dimensions of the image. This is only required for a full size image, when you are specifying a URI and also a preview image. When displaying a bitmap object, or not using a preview, you do not need to declare the image dimensions. Note if the declared dimensions are found to be incorrect, the view will reset.
 * @param sWidth width of the source image.
 * @param sHeight height of the source image.
 * @return this instance for chaining.
 */
@NonNull public ImageSource dimensions(int sWidth,int sHeight){
  if (bitmap == null) {
    this.sWidth=sWidth;
    this.sHeight=sHeight;
  }
  setInvariants();
  return this;
}
