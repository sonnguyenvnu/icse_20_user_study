@OnClick(R.id.tagsIcon) void onTagsClick(){
  if (topicsList.getAdapter().getItemCount() > 0) {
    TransitionManager.beginDelayedTransition(topicsList);
    topicsList.setVisibility(topicsList.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
  }
}
