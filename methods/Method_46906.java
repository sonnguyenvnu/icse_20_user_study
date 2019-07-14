@Override protected void onPostExecute(Pair<String,List<PieEntry>> data){
  if (data == null) {
    chart.setVisibility(View.GONE);
    return;
  }
  updateChart(data.first,data.second);
  chart.notifyDataSetChanged();
  chart.invalidate();
}
