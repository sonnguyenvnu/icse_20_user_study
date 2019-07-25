@Provides @MetadataScope public List<Exposed> components(final PrimaryComponent primary,final MetadataBackendReporter reporter){
  final List<Exposed> results=new ArrayList<>();
  final ModuleIdBuilder idBuilder=new ModuleIdBuilder();
  for (  final MetadataModule m : backends) {
    final String id=idBuilder.buildId(m);
    final MetadataModule.Depends depends=new MetadataModule.Depends(reporter);
    results.add(m.module(primary,depends,id));
  }
  return results;
}
