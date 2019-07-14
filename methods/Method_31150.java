/** 
 * Sets the target version up to which Flyway should consider migrations. Migrations with a higher version number will be ignored.
 * @param target The target version up to which Flyway should consider migrations.The special value  {@code current} designates the current version of the schema. (default: the latestversion)
 */
public void setTargetAsString(String target){
  this.target=MigrationVersion.fromVersion(target);
}
