/** 
 * Apply a pull to relevant overscroll glow effects
 */
private void pullGlows(float x,float overscrollX,float y,float overscrollY){
  boolean invalidate=false;
  if (overscrollX < 0) {
    ensureLeftGlow();
    EdgeEffectCompat.onPull(mLeftGlow,-overscrollX / getWidth(),1f - y / getHeight());
    invalidate=true;
  }
 else   if (overscrollX > 0) {
    ensureRightGlow();
    EdgeEffectCompat.onPull(mRightGlow,overscrollX / getWidth(),y / getHeight());
    invalidate=true;
  }
  if (overscrollY < 0) {
    ensureTopGlow();
    EdgeEffectCompat.onPull(mTopGlow,-overscrollY / getHeight(),x / getWidth());
    invalidate=true;
  }
 else   if (overscrollY > 0) {
    ensureBottomGlow();
    EdgeEffectCompat.onPull(mBottomGlow,overscrollY / getHeight(),1f - x / getWidth());
    invalidate=true;
  }
  if (invalidate || overscrollX != 0 || overscrollY != 0) {
    ViewCompat.postInvalidateOnAnimation(this);
  }
}
