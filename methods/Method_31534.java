static String getCategory(MigrationInfo migrationInfo){
  if (migrationInfo.getType().isSynthetic()) {
    return "";
  }
  if (migrationInfo.getVersion() == null) {
    return "Repeatable";
  }
  return "Versioned";
}
