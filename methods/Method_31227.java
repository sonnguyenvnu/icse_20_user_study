private boolean descriptionUpdateNeeded(ResolvedMigration resolved,AppliedMigration applied){
  return !Objects.equals(resolved.getDescription(),applied.getDescription());
}
