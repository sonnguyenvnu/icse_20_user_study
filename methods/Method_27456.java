private void onToggle(boolean expanded,boolean animate){
  if (animate) {
    TransitionManager.beginDelayedTransition(viewGroup,new ChangeBounds());
  }
  toggle.setRotation(!expanded ? 0.0F : 180F);
  commentOptions.setVisibility(!expanded ? View.GONE : View.VISIBLE);
  reactionsList.setVisibility(expanded ? View.GONE : reactionsList.getTag() == null || (!((Boolean)reactionsList.getTag())) ? View.GONE : View.VISIBLE);
}
