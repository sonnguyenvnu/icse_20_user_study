private void setAmountPledgedTextViewText(final @NonNull Pair<Project,Float> projectAndAmount,final TextView textview){
  final String amountString=this.ksCurrency.format(projectAndAmount.second,projectAndAmount.first);
  textview.setText(amountString);
}
