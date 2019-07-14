private boolean checksumUpdateNeeded(ResolvedMigration resolved,AppliedMigration applied){
  return !Objects.equals(resolved.getChecksum(),applied.getChecksum());
}
