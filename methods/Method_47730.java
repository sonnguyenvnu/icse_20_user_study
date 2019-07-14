private void initEditMode(){
  int color=PaletteUtils.getAndroidTestColor(1);
  title.setTextColor(color);
  streakChart.setColor(color);
  streakChart.populateWithRandomData();
}
