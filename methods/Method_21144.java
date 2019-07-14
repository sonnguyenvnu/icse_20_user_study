private void setBackingInfoView(final @NonNull Pair<Backing,Project> backingAndProject){
  final String pledgeAmount=this.ksCurrency.format(backingAndProject.first.amount(),backingAndProject.second,RoundingMode.HALF_UP);
  final String pledgeDate=DateTimeUtils.relative(this,this.ksString,backingAndProject.first.pledgedAt());
  this.backingAmountTextViewText.setText(Html.fromHtml(this.ksString.format(this.pledgeAmountPledgedOnPledgeDateString,"pledge_amount",pledgeAmount,"pledge_date",pledgeDate)));
}
