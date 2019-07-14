private void setSuccessfullyFundedDateTextView(final @NonNull DateTime projectSuccessfulAt){
  this.fundingSuccessfulTextViewDate.setText(DateTimeUtils.relative(context(),this.ksString,projectSuccessfulAt));
}
