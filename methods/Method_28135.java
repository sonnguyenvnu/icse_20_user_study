@Override public void onMilestoneSelected(@NonNull MilestoneModel milestoneModel){
  Logger.e(milestoneModel.getTitle(),milestoneModel.getDescription(),milestoneModel.getNumber());
  this.milestoneModel=milestoneModel;
  milestoneTitle.setText(milestoneModel.getTitle());
  if (!InputHelper.isEmpty(milestoneModel.getDescription())) {
    milestoneDescription.setText(milestoneModel.getDescription());
    milestoneDescription.setVisibility(View.VISIBLE);
  }
 else {
    milestoneDescription.setText("");
    milestoneDescription.setVisibility(View.GONE);
  }
}
