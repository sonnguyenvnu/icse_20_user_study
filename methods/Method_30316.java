private void onStateChanged(){
  boolean hasRating=getState() != ItemCollectionState.TODO;
  ViewUtils.setVisibleOrGone(mRatingLayout,hasRating);
  updateOptionsMenu();
}
