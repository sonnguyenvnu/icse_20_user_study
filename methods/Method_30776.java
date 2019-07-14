/** 
 * Initializes local dynamic properties from state. This should be called after significant state changes, e.g. from the One True Constructor and after inflating or applying a theme.
 */
private void updateLocalState(Resources res){
  if (mState != null && mState.mDrawableState != null) {
    final Drawable dr=mState.mDrawableState.newDrawable(res);
    setDrawable(dr);
  }
}
