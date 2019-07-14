@Override public void onMerge(@NonNull String msg,@NonNull String mergeMethod){
  if (isMergeable() && (isCollaborator() || isRepoOwner())) {
    if (getPullRequest() == null || getPullRequest().getHead() == null || getPullRequest().getHead().getSha() == null)     return;
    MergeRequestModel mergeRequestModel=new MergeRequestModel();
    mergeRequestModel.setSha(getPullRequest().getHead().getSha());
    mergeRequestModel.setCommitMessage(msg);
    mergeRequestModel.setMergeMethod(mergeMethod.toLowerCase());
    manageDisposable(RxHelper.getObservable(RestProvider.getPullRequestService(isEnterprise()).mergePullRequest(login,repoId,issueNumber,mergeRequestModel)).doOnSubscribe(disposable -> sendToView(view -> view.showProgress(0))).subscribe(mergeResponseModel -> {
      if (mergeResponseModel.isMerged()) {
        pullRequest.setMerged(true);
        sendToView(view -> updateTimeline(view,R.string.success_merge));
      }
 else {
        sendToView(view -> view.showErrorMessage(mergeResponseModel.getMessage()));
      }
    }
,throwable -> sendToView(view -> view.showErrorMessage(throwable.getMessage()))));
  }
}
