private void onCallCountApi(@NonNull IssueState issueState){
  manageDisposable(RxHelper.getObservable(RestProvider.getIssueService(isEnterprise()).getIssuesWithCount(RepoQueryProvider.getIssuesPullRequestQuery(login,repoId,issueState,false),1)).subscribe(pullRequestPageable -> sendToView(view -> view.onUpdateCount(pullRequestPageable.getTotalCount())),Throwable::printStackTrace));
}
