@Override public Integer getExecutionTime(){
  if (appliedMigration != null) {
    return appliedMigration.getExecutionTime();
  }
  return null;
}
