private boolean typeUpdateNeeded(ResolvedMigration resolved,AppliedMigration applied){
  return !Objects.equals(resolved.getType(),applied.getType());
}
