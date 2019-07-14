@Override public void bind(@NonNull MilestoneModel milestoneModel){
  title.setText(milestoneModel.getTitle());
  notificationTitle.setText(milestoneModel.getDescription());
  if (milestoneModel.getDueOn() != null) {
    date.setText(ParseDateFormat.getTimeAgo(milestoneModel.getDueOn()));
  }
 else   if (milestoneModel.getCreatedAt() != null) {
    date.setText(ParseDateFormat.getTimeAgo(milestoneModel.getCreatedAt()));
  }
}
