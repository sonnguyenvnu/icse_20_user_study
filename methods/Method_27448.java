@Override public void bind(@NonNull TimelineModel model){
  GroupedReviewModel groupedReviewModel=model.getGroupedReviewModel();
  this.pathText=groupedReviewModel.getDiffText();
  name.setText(groupedReviewModel.getPath());
  stateImage.setImageResource(R.drawable.ic_eye);
  if (groupedReviewModel.getComments() == null || groupedReviewModel.getComments().isEmpty()) {
    nestedRecyclerView.setVisibility(View.GONE);
    nestedRecyclerView.setAdapter(null);
  }
 else {
    nestedRecyclerView.setVisibility(View.VISIBLE);
    nestedRecyclerView.setAdapter(new ReviewCommentsAdapter(groupedReviewModel.getComments(),this,onToggleView,reactionsCallback,repoOwner,poster));
    nestedRecyclerView.addDivider();
  }
  onToggle(onToggleView.isCollapsed(getId()),false);
}
