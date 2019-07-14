/** 
 * Whether to automatically call validate or not when running migrate.
 * @param validateOnMigrate {@code true} if validate should be called. {@code false} if not. (default: {@code true})
 */
public FluentConfiguration validateOnMigrate(boolean validateOnMigrate){
  config.setValidateOnMigrate(validateOnMigrate);
  return this;
}
