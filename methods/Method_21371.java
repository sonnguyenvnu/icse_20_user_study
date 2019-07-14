private void setBackersTextView(final int count){
  final String backersCountText=this.ksString.format("rewards_info_backer_count_backers",count,"backer_count",NumberUtils.format(count));
  this.backersTextView.setText(backersCountText);
}
