/** 
 * Validates this migrationInfo for consistency.
 * @return The error message, or {@code null} if everything is fine.
 */
public String validate(){
  MigrationState state=getState();
  if (MigrationState.ABOVE_TARGET.equals(state)) {
    return null;
  }
  if (state.isFailed() && (!context.future || MigrationState.FUTURE_FAILED != state)) {
    if (getVersion() == null) {
      return "Detected failed repeatable migration: " + getDescription();
    }
    return "Detected failed migration to version " + getVersion() + " (" + getDescription() + ")";
  }
  if ((resolvedMigration == null) && !appliedMigration.getType().isSynthetic() && (appliedMigration.getVersion() != null) && (!context.missing || (MigrationState.MISSING_SUCCESS != state && MigrationState.MISSING_FAILED != state)) && (!context.future || (MigrationState.FUTURE_SUCCESS != state && MigrationState.FUTURE_FAILED != state))) {
    return "Detected applied migration not resolved locally: " + getVersion();
  }
  if (!context.pending && MigrationState.PENDING == state || (!context.ignored && MigrationState.IGNORED == state)) {
    if (getVersion() != null) {
      return "Detected resolved migration not applied to database: " + getVersion();
    }
    return "Detected resolved repeatable migration not applied to database: " + getDescription();
  }
  if (!context.pending && MigrationState.OUTDATED == state) {
    return "Detected outdated resolved repeatable migration that should be re-applied to database: " + getDescription();
  }
  if (resolvedMigration != null && appliedMigration != null) {
    String migrationIdentifier=appliedMigration.getVersion() == null ? appliedMigration.getScript() : "version " + appliedMigration.getVersion();
    if (getVersion() == null || getVersion().compareTo(context.baseline) > 0) {
      if (resolvedMigration.getType() != appliedMigration.getType()) {
        return createMismatchMessage("type",migrationIdentifier,appliedMigration.getType(),resolvedMigration.getType());
      }
      if (resolvedMigration.getVersion() != null || (context.pending && MigrationState.OUTDATED != state && MigrationState.SUPERSEDED != state)) {
        if (!Objects.equals(resolvedMigration.getChecksum(),appliedMigration.getChecksum())) {
          return createMismatchMessage("checksum",migrationIdentifier,appliedMigration.getChecksum(),resolvedMigration.getChecksum());
        }
      }
      if (!AbbreviationUtils.abbreviateDescription(resolvedMigration.getDescription()).equals(appliedMigration.getDescription())) {
        return createMismatchMessage("description",migrationIdentifier,appliedMigration.getDescription(),resolvedMigration.getDescription());
      }
    }
  }
  if (!context.pending && MigrationState.PENDING == state && resolvedMigration instanceof ResolvedMigrationImpl) {
    ((ResolvedMigrationImpl)resolvedMigration).validate();
  }
  return null;
}
