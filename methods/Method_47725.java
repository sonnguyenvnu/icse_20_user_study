private void refreshScore(){
  float todayPercentage=cache.todayScore;
  float monthDiff=todayPercentage - cache.lastMonthScore;
  float yearDiff=todayPercentage - cache.lastYearScore;
  scoreRing.setPercentage(todayPercentage);
  scoreLabel.setText(String.format("%.0f%%",todayPercentage * 100));
  monthDiffLabel.setText(formatPercentageDiff(monthDiff));
  yearDiffLabel.setText(formatPercentageDiff(yearDiff));
  totalCountLabel.setText(String.valueOf(cache.totalCount));
  StyledResources res=new StyledResources(getContext());
  int inactiveColor=res.getColor(R.attr.mediumContrastTextColor);
  monthDiffLabel.setTextColor(monthDiff >= 0 ? color : inactiveColor);
  yearDiffLabel.setTextColor(yearDiff >= 0 ? color : inactiveColor);
  totalCountLabel.setTextColor(yearDiff >= 0 ? color : inactiveColor);
  postInvalidate();
}
