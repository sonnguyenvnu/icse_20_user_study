private void setStatsContentDescription(final @NonNull Project project){
  final String backersCountContentDescription=NumberUtils.format(project.backersCount()) + " " + this.backersString;
  final String pledgedContentDescription=this.pledgedTextView.getText() + " " + this.goalTextView.getText();
  final String deadlineCountdownContentDescription=this.deadlineCountdownTextView.getText() + " " + this.deadlineCountdownUnitTextView.getText();
  this.backersCountTextView.setContentDescription(backersCountContentDescription);
  this.pledgedTextView.setContentDescription(pledgedContentDescription);
  this.deadlineCountdownTextView.setContentDescription(deadlineCountdownContentDescription);
}
