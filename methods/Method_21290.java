private void setTimeRemainingTextTextView(final @NonNull Project currentProject){
  this.timeRemainingTextTextView.setText(ProjectUtils.deadlineCountdownDetail(currentProject,this.context(),this.ksString));
}
