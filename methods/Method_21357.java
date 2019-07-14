private void setSuccessfulProjectStateView(final @NonNull DateTime stateChangedAt){
  this.projectStateHeaderTextView.setText(this.fundedString);
  this.projectStateSubheadTextView.setText(this.ksString.format(this.successfullyFundedOnDeadlineString,"deadline",mediumDate(stateChangedAt)));
}
