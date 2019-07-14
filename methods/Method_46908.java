private void updateChart(String totalSpace,List<PieEntry> entries){
  boolean isDarkTheme=appTheme.getMaterialDialogTheme() == Theme.DARK;
  PieDataSet set=new PieDataSet(entries,null);
  set.setColors(COLORS);
  set.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
  set.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
  set.setSliceSpace(5f);
  set.setAutomaticallyDisableSliceSpacing(true);
  set.setValueLinePart2Length(1.05f);
  set.setSelectionShift(0f);
  PieData pieData=new PieData(set);
  pieData.setValueFormatter(new GeneralDialogCreation.SizeFormatter(context));
  pieData.setValueTextColor(isDarkTheme ? Color.WHITE : Color.BLACK);
  chart.setCenterText(new SpannableString(context.getString(R.string.total) + "\n" + totalSpace));
  chart.setData(pieData);
}
