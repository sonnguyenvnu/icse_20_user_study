public static Collection<SNode> instances(SearchScope scope,SAbstractConcept concept,boolean exact){
  return FindUsagesManager.getInstance().findInstances(scope,Collections.singleton(concept),exact,new EmptyProgressMonitor());
}
