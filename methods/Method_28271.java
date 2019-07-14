@Override public void onPutMilestones(@NonNull MilestoneModel milestone){
  pullRequest.setMilestone(milestone);
  IssueRequestModel issueRequestModel=IssueRequestModel.clone(pullRequest,false);
  makeRestCall(RestProvider.getPullRequestService(isEnterprise()).editIssue(login,repoId,issueNumber,issueRequestModel),pr -> {
    this.pullRequest.setMilestone(pr.getMilestone());
    manageObservable(pr.save(pullRequest).toObservable());
    sendToView(view -> updateTimeline(view,R.string.labels_added_successfully));
  }
);
}
