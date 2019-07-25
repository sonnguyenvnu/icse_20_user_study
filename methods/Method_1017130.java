@Provides @MetricScope public List<MetricModule.Exposed> components(final CorePrimaryComponent primary,final MetricBackendReporter reporter){
  final List<MetricModule.Exposed> exposed=new ArrayList<>();
  final ModuleIdBuilder idBuilder=new ModuleIdBuilder();
  for (  final MetricModule m : backends) {
    final String id=idBuilder.buildId(m);
    final MetricModule.Depends depends=new MetricModule.Depends(reporter);
    exposed.add(m.module(primary,depends,id));
  }
  return exposed;
}
