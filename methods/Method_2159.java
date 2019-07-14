/** 
 * Gets the bounds used to limit the translation, in view-absolute coordinates. <p> These bounds are passed to the zoomable controller in order to limit the translation. The image is attempted to be centered within the limit bounds if the transformed image is smaller. There will be no empty spaces within the limit bounds if the transformed image is bigger. This applies to each dimension (horizontal and vertical) independently. <p> Unless overridden by a subclass, these bounds are same as the view bounds.
 */
protected void getLimitBounds(RectF outBounds){
  outBounds.set(0,0,getWidth(),getHeight());
}
