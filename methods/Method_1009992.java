private void load(boolean force){
  PullRequestService prService=ServiceFactory.get(PullRequestService.class,force);
  IssueService issueService=ServiceFactory.get(IssueService.class,force);
  Single<PullRequest> prSingle=prService.getPullRequest(mRepoOwner,mRepoName,mPullRequestNumber).map(ApiHelpers::throwOnFailure);
  Single<Issue> issueSingle=issueService.getIssue(mRepoOwner,mRepoName,mPullRequestNumber).map(ApiHelpers::throwOnFailure);
  Single<Boolean> isCollaboratorSingle=SingleFactory.isAppUserRepoCollaborator(mRepoOwner,mRepoName,force);
  Single.zip(issueSingle,prSingle,isCollaboratorSingle,Triplet::create).compose(makeLoaderSingle(0,force)).subscribe(result -> {
    mIssue=result.first;
    mPullRequest=result.second;
    mIsCollaborator=result.third;
    fillHeader();
    setContentShown(true);
    invalidateTabs();
    updateFabVisibility();
    supportInvalidateOptionsMenu();
    if (mInitialPage >= 0 && mInitialPage < TITLES.length) {
      getPager().setCurrentItem(mInitialPage);
      mInitialPage=-1;
    }
  }
,this::handleLoadFailure);
}
