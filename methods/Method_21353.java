private void setConvertedCurrencyView(final @NonNull Pair<String,String> pledgedAndGoal){
  this.conversionTextView.setText(this.ksString.format(this.convertedFromString,"pledged",pledgedAndGoal.first,"goal",pledgedAndGoal.second));
}
