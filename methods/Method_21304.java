private void setPledgedColumnValue(final @NonNull Pair<Project,Float> projectAndPledgedForReward){
  final String goalString=this.ksCurrency.format(projectAndPledgedForReward.second,projectAndPledgedForReward.first);
  this.amountForRewardPledgedTextView.setText(goalString);
}
