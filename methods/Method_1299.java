/** 
 * Detaches the currently attached DraweeSpanStringBuilder (if there is one) so that this view can be used as a normal text view instead.
 */
public void detachCurrentDraweeSpanStringBuilder(){
  if (mDraweeStringBuilder != null) {
    mDraweeStringBuilder.onDetachFromView(this);
  }
  mDraweeStringBuilder=null;
}
