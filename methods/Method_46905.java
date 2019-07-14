@Override protected void onProgressUpdate(Long[] dataArray){
  if (dataArray[0] != -1 && dataArray[0] != 0) {
    long totalSpace=dataArray[0];
    List<PieEntry> entries=createEntriesFromArray(new long[]{dataArray[0],dataArray[1],dataArray[2]},true);
    updateChart(Formatter.formatFileSize(context,totalSpace),entries);
    chart.notifyDataSetChanged();
    chart.invalidate();
  }
}
