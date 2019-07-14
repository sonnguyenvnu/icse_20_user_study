@SuppressWarnings("deprecation") @Override public RecyclerView.LayoutParams generateDefaultLayoutParams(){
  if (mOrientation == HORIZONTAL) {
    return new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT);
  }
 else {
    return new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
  }
}
