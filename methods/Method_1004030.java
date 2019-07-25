@Override public List<MetricFamilySamples> collect(){
  GaugeMetricFamily seleniumContainersStateMetric=new GaugeMetricFamily("selenium_containers","The number of Selenium Containers broken down by state",singletonList("state"));
  Map<States,Long> containerStates=startedContainers.values().stream().collect(groupingBy(s -> {
    return s.isStarted() ? States.RUNNING : States.STARTING;
  }
,counting()));
  STATES.stream().forEach(s -> seleniumContainersStateMetric.addMetric(singletonList(s.name().toLowerCase()),Optional.ofNullable(containerStates.get(s)).orElse(0L)));
  List<MetricFamilySamples> mfs=new ArrayList<MetricFamilySamples>();
  mfs.add(seleniumContainersStateMetric);
  return mfs;
}
