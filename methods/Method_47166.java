/** 
 * Initialize chart for the first time
 * @param totalBytes maximum value for x-axis
 */
private void chartInit(long totalBytes){
  mLineChart.setBackgroundColor(accentColor);
  mLineChart.getLegend().setEnabled(false);
  mLineChart.getDescription().setEnabled(false);
  XAxis xAxis=mLineChart.getXAxis();
  YAxis yAxisLeft=mLineChart.getAxisLeft();
  mLineChart.getAxisRight().setEnabled(false);
  yAxisLeft.setTextColor(Color.WHITE);
  yAxisLeft.setAxisLineColor(Color.TRANSPARENT);
  yAxisLeft.setTypeface(Typeface.DEFAULT_BOLD);
  yAxisLeft.setGridColor(Utils.getColor(getContext(),R.color.white_translucent));
  xAxis.setAxisMaximum(FileUtils.readableFileSizeFloat(totalBytes));
  xAxis.setAxisMinimum(0.0f);
  xAxis.setAxisLineColor(Color.TRANSPARENT);
  xAxis.setGridColor(Color.TRANSPARENT);
  xAxis.setTextColor(Color.WHITE);
  xAxis.setTypeface(Typeface.DEFAULT_BOLD);
  mLineChart.setData(mLineData);
  mLineChart.invalidate();
}
