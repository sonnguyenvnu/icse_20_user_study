@Override public void onRemoveReviewComment(int groupPosition,int commentPosition){
  hideProgress();
  TimelineModel timelineModel=adapter.getItem(groupPosition);
  if (timelineModel != null && timelineModel.getGroupedReviewModel() != null) {
    if (timelineModel.getGroupedReviewModel().getComments() != null) {
      timelineModel.getGroupedReviewModel().getComments().remove(commentPosition);
      if (timelineModel.getGroupedReviewModel().getComments().isEmpty()) {
        adapter.removeItem(groupPosition);
      }
 else {
        adapter.notifyItemChanged(groupPosition);
      }
    }
  }
}
