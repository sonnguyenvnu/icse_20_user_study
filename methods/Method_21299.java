private void setPledgedColumnValue(final @NonNull Pair<Project,Float> projectAndPledgedForReferrer){
  final String goalString=this.ksCurrency.format(projectAndPledgedForReferrer.second,projectAndPledgedForReferrer.first);
  this.amountPledgedForReferrerTextView.setText(goalString);
}
