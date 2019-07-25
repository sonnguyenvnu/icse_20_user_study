public Iterable<Cluster> clusterize(Iterable<? extends IResource> res){
  final List<MResource> mres=Sequence.fromIterable(res).ofType(MResource.class).toListSequence();
  Iterable<SModule> mods=ListSequence.fromList(mres).select(new ISelector<MResource,SModule>(){
    public SModule select(    MResource r){
      return r.module();
    }
  }
);
  Iterable<IResource> rest=Sequence.fromIterable(((Iterable<IResource>)res)).subtract(ListSequence.fromList(mres));
  ModulesCluster clst=new ModulesCluster(myLanguageRegistry);
  Iterable<? extends Iterable<SModule>> moduleBuildOrder=clst.buildOrder(mods);
  Iterable<? extends Iterable<MResource>> mresBuildOrder=Sequence.fromIterable(moduleBuildOrder).select(new ISelector<Iterable<SModule>,ISequence<MResource>>(){
    public ISequence<MResource> select(    final Iterable<SModule> cl){
      return ListSequence.fromList(mres).where(new IWhereFilter<MResource>(){
        public boolean accept(        MResource r){
          return Sequence.fromIterable(cl).contains(r.module());
        }
      }
);
    }
  }
);
  List<Cluster> result=ListSequence.fromList(new ArrayList<Cluster>());
  for (  Iterable<MResource> s : mresBuildOrder) {
    ListSequence.fromList(result).addElement(new Cluster(s,allLanguagesToActivateFacets(s,clst),myLanguageRegistry));
  }
  if (Sequence.fromIterable(rest).isNotEmpty()) {
    ListSequence.fromList(result).addElement(new Cluster(rest,ListSequence.fromList(new ArrayList<SLanguage>()),myLanguageRegistry));
  }
  return result;
}
