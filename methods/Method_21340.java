private void setCanceledTextView(final @NonNull DateTime projectCanceledAt){
  this.fundingUnsuccessfulTextViewDate.setText(DateTimeUtils.relative(context(),this.ksString,projectCanceledAt));
  this.fundingUnsuccessfulTextView.setText(this.fundingCanceledString);
}
