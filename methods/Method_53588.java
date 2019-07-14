/** 
 * Append tag to this group.
 * @param tag the tag to append.
 */
protected void appendTag(CharSequence tag){
  final TagView newTag=new TagView(getContext(),TagView.STATE_NORMAL,tag);
  newTag.setOnClickListener(mInternalTagClickListener);
  addView(newTag);
}
