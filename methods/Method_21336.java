private void setDeadlineCountdownText(final @NonNull Project project){
  this.deadlineCountdownUnitTextView.setText(ProjectUtils.deadlineCountdownDetail(project,context(),this.ksString));
}
