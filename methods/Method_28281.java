private void updateTimeline(PullRequestPagerMvp.View view,int assignee_added){
  view.showMessage(R.string.success,assignee_added);
  view.onUpdateTimeline();
}
