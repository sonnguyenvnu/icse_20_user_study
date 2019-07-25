public void bind(PullRequestMarker sourceMarker,PullRequestMarker targetMarker,GitReference sourceReference){
  mSourceMarker=sourceMarker;
  mTargetMarker=targetMarker;
  formatMarkerText(mSourceBranchView,R.string.pull_request_from,sourceMarker,sourceReference != null);
  formatMarkerText(mTargetBranchView,R.string.pull_request_to,targetMarker,true);
}
