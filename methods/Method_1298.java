/** 
 * Bind the given string builder to this view.
 * @param draweeSpanStringBuilder the builder to attach to
 */
public void setDraweeSpanStringBuilder(DraweeSpanStringBuilder draweeSpanStringBuilder){
  setText(draweeSpanStringBuilder,BufferType.SPANNABLE);
  mDraweeStringBuilder=draweeSpanStringBuilder;
  if (mDraweeStringBuilder != null && mIsAttached) {
    mDraweeStringBuilder.onAttachToView(this);
  }
}
