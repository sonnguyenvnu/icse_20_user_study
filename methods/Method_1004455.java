@Override public List<MetricFamilySamples> collect(){
  List<MetricFamilySamples.Sample> samples=new ArrayList<>(children.size());
  for (  Map.Entry<List<String>,Child> c : children.entrySet()) {
    samples.add(new MetricFamilySamples.Sample(fullname,labelNames,c.getKey(),c.getValue().get()));
  }
  return familySamplesList(Type.GAUGE,samples);
}
