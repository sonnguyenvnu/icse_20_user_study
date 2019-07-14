@Override protected @Nullable Pair<Integer,Integer> exitTransition(){
  return this.backButton.getVisibility() == View.VISIBLE ? TransitionUtils.slideInFromLeft() : null;
}
