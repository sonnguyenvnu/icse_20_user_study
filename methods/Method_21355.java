private void setProjectDisclaimerGoalNotReachedString(final @NonNull Pair<String,DateTime> goalAndDeadline){
  this.projectDisclaimerTextView.setText(this.ksString.format(this.projectDisclaimerGoalNotReachedString,"goal_currency",goalAndDeadline.first,"deadline",mediumDateShortTime(goalAndDeadline.second)));
}
