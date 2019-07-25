@Override public List<MetricFamilySamples> describe(){
  List<MetricFamilySamples> list=new ArrayList<>();
  list.add(new GaugeMetricFamily(fullname,help,labelNames));
  return list;
}
