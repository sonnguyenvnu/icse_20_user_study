private void setTitleCopy(final boolean referrersTitleIsTopTen){
  final String formattedTopRewards=StringUtils.sentenceCase(this.topRewardsString);
  this.rewardsTitleTextView.setText(referrersTitleIsTopTen ? this.topTenRewardsString : formattedTopRewards);
}
