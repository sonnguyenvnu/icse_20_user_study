@Override public void onAddComment(CommentRequestModel comment){
  getPresenter().onAddComment(comment);
  AnimHelper.mimicFabVisibility(getPresenter().hasReviewComments(),prReviewHolder,null);
  reviewsCount.setText(String.format("%s",getPresenter().getCommitComment().size()));
  Logger.e(reviewsCount.getText(),prReviewHolder.getVisibility());
}
