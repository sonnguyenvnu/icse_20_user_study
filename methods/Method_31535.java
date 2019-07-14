@Override public Integer getChecksum(){
  if (appliedMigration != null) {
    return appliedMigration.getChecksum();
  }
  return resolvedMigration.getChecksum();
}
