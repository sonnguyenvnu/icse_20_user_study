/** 
 * As  {@code setCurrent}, but without invalidating a drawable. Subclasses are responsible to call {@code invalidateSelf} on their own.
 * @param newDelegate
 * @return the previous delegate
 */
protected @Nullable Drawable setCurrentWithoutInvalidate(@Nullable Drawable newDelegate){
  Drawable previousDelegate=mCurrentDelegate;
  DrawableUtils.setCallbacks(previousDelegate,null,null);
  DrawableUtils.setCallbacks(newDelegate,null,null);
  DrawableUtils.setDrawableProperties(newDelegate,mDrawableProperties);
  DrawableUtils.copyProperties(newDelegate,this);
  DrawableUtils.setCallbacks(newDelegate,this,this);
  mCurrentDelegate=newDelegate;
  return previousDelegate;
}
