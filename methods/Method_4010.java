@VisibleForTesting void initFastScroller(StateListDrawable verticalThumbDrawable,Drawable verticalTrackDrawable,StateListDrawable horizontalThumbDrawable,Drawable horizontalTrackDrawable){
  if (verticalThumbDrawable == null || verticalTrackDrawable == null || horizontalThumbDrawable == null || horizontalTrackDrawable == null) {
    throw new IllegalArgumentException("Trying to set fast scroller without both required drawables." + exceptionLabel());
  }
  Resources resources=getContext().getResources();
  new FastScroller(this,verticalThumbDrawable,verticalTrackDrawable,horizontalThumbDrawable,horizontalTrackDrawable,AndroidUtilities.dp(8),AndroidUtilities.dp(50),0);
}
