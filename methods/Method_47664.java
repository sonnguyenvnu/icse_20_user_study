public void setStreaks(List<Streak> streaks){
  this.streaks=streaks;
  initColors();
  updateMaxMinLengths();
  requestLayout();
}
