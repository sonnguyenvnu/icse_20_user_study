private void addPrReview(@NonNull View view){
  PullRequest pullRequest=getPresenter().getPullRequest();
  if (pullRequest == null)   return;
  User author=pullRequest.getUser() != null ? pullRequest.getUser() : pullRequest.getHead() != null && pullRequest.getHead().getAuthor() != null ? pullRequest.getHead().getAuthor() : pullRequest.getUser();
  if (author == null)   return;
  ReviewRequestModel requestModel=new ReviewRequestModel();
  requestModel.setComments(getPresenter().getCommitComment().isEmpty() ? null : getPresenter().getCommitComment());
  requestModel.setCommitId(pullRequest.getHead().getSha());
  boolean isAuthor=Login.getUser().getLogin().equalsIgnoreCase(author.getLogin());
  ReviewChangesActivity.Companion.startForResult(requestModel,getPresenter().getRepoId(),getPresenter().getLogin(),pullRequest.getNumber(),isAuthor,isEnterprise(),pullRequest.isMerged() || pullRequest.getState() == IssueState.closed).show(getSupportFragmentManager(),ReviewChangesActivity.class.getSimpleName());
}
