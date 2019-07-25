@Provides @SuggestScope public List<SuggestModule.Exposed> components(SuggestBackendReporter reporter,PrimaryComponent primary){
  final ArrayList<SuggestModule.Exposed> results=new ArrayList<>();
  final ModuleIdBuilder idBuilder=new ModuleIdBuilder();
  for (  final SuggestModule m : backends) {
    final String id=idBuilder.buildId(m);
    final SuggestModule.Depends depends=new SuggestModule.Depends(reporter);
    results.add(m.module(primary,depends,id));
  }
  return results;
}
