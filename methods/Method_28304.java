@Override public void onHandleDeletion(@Nullable Bundle bundle){
  if (getView() == null || getView().getPullRequest() == null)   return;
  if (bundle != null) {
    PullRequest pullRequest=getView().getPullRequest();
    String login=pullRequest.getLogin();
    String repoId=pullRequest.getRepoId();
    long commId=bundle.getLong(BundleConstant.EXTRA,0);
    boolean isReviewComment=bundle.getBoolean(BundleConstant.YES_NO_EXTRA);
    if (commId != 0 && !isReviewComment) {
      makeRestCall(RestProvider.getIssueService(isEnterprise()).deleteIssueComment(login,repoId,commId),booleanResponse -> sendToView(view -> {
        if (booleanResponse.code() == 204) {
          Comment comment=new Comment();
          comment.setId(commId);
          view.onRemove(TimelineModel.constructComment(comment));
        }
 else {
          view.showMessage(R.string.error,R.string.error_deleting_comment);
        }
      }
));
    }
 else {
      int groupPosition=bundle.getInt(BundleConstant.EXTRA_TWO);
      int commentPosition=bundle.getInt(BundleConstant.EXTRA_THREE);
      makeRestCall(RestProvider.getReviewService(isEnterprise()).deleteComment(login,repoId,commId),booleanResponse -> sendToView(view -> {
        if (booleanResponse.code() == 204) {
          view.onRemoveReviewComment(groupPosition,commentPosition);
        }
 else {
          view.showMessage(R.string.error,R.string.error_deleting_comment);
        }
      }
));
    }
  }
}
