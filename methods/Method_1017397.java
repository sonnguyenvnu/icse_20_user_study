private static boolean compare(@NotNull Project project,@NotNull String version,@NotNull Comparator comparator){
  CachedValue<Set<String>> cache=project.getUserData(CACHE);
  if (cache == null) {
    cache=CachedValuesManager.getManager(project).createCachedValue(() -> CachedValueProvider.Result.create(getVersions(project),PsiModificationTracker.MODIFICATION_COUNT),false);
    project.putUserData(CACHE,cache);
  }
  for (  String s : cache.getValue()) {
    if (comparator.accepts(s)) {
      return true;
    }
  }
  return false;
}
