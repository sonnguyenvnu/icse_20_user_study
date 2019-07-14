/** 
 * Returns whether the scroll can happen in all directions. I.e. the image is not on any edge.
 */
private boolean canScrollInAllDirection(){
  return mTransformedImageBounds.left < mViewBounds.left - EPS && mTransformedImageBounds.top < mViewBounds.top - EPS && mTransformedImageBounds.right > mViewBounds.right + EPS && mTransformedImageBounds.bottom > mViewBounds.bottom + EPS;
}
