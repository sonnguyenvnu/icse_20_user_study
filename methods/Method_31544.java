/** 
 * Validate all migrations for consistency.
 * @return The error message, or {@code null} if everything is fine.
 */
public String validate(){
  for (  MigrationInfoImpl migrationInfo : migrationInfos) {
    String message=migrationInfo.validate();
    if (message != null) {
      return message;
    }
  }
  return null;
}
