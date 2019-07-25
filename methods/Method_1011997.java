@NotNull @Override public Iterator<NavigationTarget> iterator(){
  return new ModelAccessHelper(myRepo).runReadAction(() -> GotoNavigationUtil.getNavigationTargets(TargetKind.ROOT,myScope,new EmptyProgressMonitor())).iterator();
}
