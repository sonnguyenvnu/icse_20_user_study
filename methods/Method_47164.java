/** 
 * Add a new entry dynamically to the chart, initializes a  {@link LineDataSet} if not done so
 * @param xValue the x-axis value, the number of bytes processed till now
 * @param yValue the y-axis value, bytes processed per sec
 */
private void addEntry(float xValue,float yValue){
  ILineDataSet dataSet=mLineData.getDataSetByIndex(0);
  if (dataSet == null) {
    dataSet=createDataSet();
    mLineData.addDataSet(dataSet);
  }
  dataSet.addEntry(new Entry(xValue,yValue));
  mLineData.notifyDataChanged();
  mLineChart.notifyDataSetChanged();
  mLineChart.invalidate();
}
