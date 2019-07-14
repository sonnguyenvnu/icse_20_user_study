@Override public List<AppliedMigration> allAppliedMigrations(){
  if (!exists()) {
    return new ArrayList<>();
  }
  refreshCache();
  return cache;
}
