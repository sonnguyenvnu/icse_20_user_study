private void onCallCountApi(@NonNull IssueState issueState){
  manageDisposable(RxHelper.getObservable(RestProvider.getPullRequestService(isEnterprise()).getPullsWithCount(RepoQueryProvider.getIssuesPullRequestQuery(login,repoId,issueState,true),0)).subscribe(pullRequestPageable -> sendToView(view -> view.onUpdateCount(pullRequestPageable.getTotalCount())),Throwable::printStackTrace));
}
