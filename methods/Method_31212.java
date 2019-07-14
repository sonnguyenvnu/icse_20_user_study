/** 
 * <p>Undoes the most recently applied versioned migration. If target is specified, Flyway will attempt to undo versioned migrations in the order they were applied until it hits one with a version below the target. If there is no versioned migration to undo, calling undo has no effect.</p> <p><i>Flyway Pro and Flyway Enterprise only</i></p> <img src="https://flywaydb.org/assets/balsamiq/command-undo.png" alt="undo">
 * @return The number of successfully undone migrations.
 * @throws FlywayException when the undo failed.
 */
public int undo() throws FlywayException {
  throw new org.flywaydb.core.internal.license.FlywayProUpgradeRequiredException("undo");
}
