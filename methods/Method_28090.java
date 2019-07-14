@Override public void onMilestoneSelected(@NonNull MilestoneModel milestoneModel){
  if (issueCallback != null)   issueCallback.onMileStoneSelected(milestoneModel);
  if (pullRequestCallback != null)   pullRequestCallback.onMileStoneSelected(milestoneModel);
  if (milestoneCallback != null)   milestoneCallback.onMilestoneSelected(milestoneModel);
}
