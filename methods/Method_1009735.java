/** 
 * Declare the dimensions of the image. This is only required for a full size image, when you are specifying a URI and also a preview image. When displaying a bitmap object, or not using a preview, you do not need to declare the image dimensions. Note if the declared dimensions are found to be incorrect, the view will reset.
 * @return this instance for chaining.
 */
public ImageSource dimensions(int sWidth,int sHeight){
  if (bitmap == null) {
    this.sWidth=sWidth;
    this.sHeight=sHeight;
  }
  setInvariants();
  return this;
}
