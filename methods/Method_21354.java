private void setProjectDisclaimerGoalReachedString(final @NonNull DateTime deadline){
  this.projectDisclaimerTextView.setText(this.ksString.format(this.projectDisclaimerGoalReachedString,"deadline",mediumDateShortTime(deadline)));
}
