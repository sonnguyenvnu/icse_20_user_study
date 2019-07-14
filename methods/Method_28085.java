@Override public void onMilestoneAdded(@NonNull MilestoneModel milestoneModel){
  hideProgress();
  onMilestoneAdded.onMilestoneAdded(milestoneModel);
  dismiss();
}
