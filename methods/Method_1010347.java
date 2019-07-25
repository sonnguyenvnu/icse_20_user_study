private static void track(SRepository repo){
  if (repo == null) {
    return;
  }
  if (!ourStructureChangeTrackers.containsKey(repo)) {
    StructureAspectChangeTracker tracker1=new StructureAspectChangeTracker(null,ourStructureAspectListener);
    ourStructureChangeTrackers.putIfAbsent(repo,tracker1);
    if (tracker1 == ourStructureChangeTrackers.get(repo)) {
      repo.addRepositoryListener(tracker1);
    }
  }
  if (!ourLifecycleTrackers.containsKey(repo)) {
    ModelLifecycleTracker tracker2=new ModelLifecycleTracker();
    ourLifecycleTrackers.putIfAbsent(repo,tracker2);
    if (tracker2 == ourLifecycleTrackers.get(repo)) {
      repo.addRepositoryListener(tracker2);
    }
  }
}
