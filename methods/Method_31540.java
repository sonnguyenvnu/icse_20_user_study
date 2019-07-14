@Override public MigrationInfo current(){
  MigrationInfo current=null;
  for (  MigrationInfoImpl migrationInfo : migrationInfos) {
    if (migrationInfo.getState().isApplied() && migrationInfo.getVersion() != null && (current == null || migrationInfo.getVersion().compareTo(current.getVersion()) > 0)) {
      current=migrationInfo;
    }
  }
  if (current != null) {
    return current;
  }
  for (int i=migrationInfos.size() - 1; i >= 0; i--) {
    MigrationInfoImpl migrationInfo=migrationInfos.get(i);
    if (migrationInfo.getState().isApplied()) {
      return migrationInfo;
    }
  }
  return null;
}
