/** 
 * Append a INPUT tag to this group. It will throw an exception if there has a previous INPUT tag.
 * @param tag the tag text.
 */
protected void appendInputTag(String tag){
  final TagView previousInputTag=getInputTag();
  if (previousInputTag != null) {
    throw new IllegalStateException("Already has a INPUT tag in group.");
  }
  final TagView newInputTag=new TagView(getContext(),TagView.STATE_INPUT,tag);
  newInputTag.setOnClickListener(mInternalTagClickListener);
  addView(newInputTag);
}
