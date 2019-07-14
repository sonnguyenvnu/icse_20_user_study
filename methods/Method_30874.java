@Override public void setVisibility(int visibility){
  super.setVisibility(visibility);
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    onVisibilityAggregated(visibility == VISIBLE);
  }
}
