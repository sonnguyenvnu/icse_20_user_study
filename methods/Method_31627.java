private static String cleanMigrationName(String migrationName,String prefix,String[] suffixes){
  for (  String suffix : suffixes) {
    if (migrationName.endsWith(suffix)) {
      return migrationName.substring(StringUtils.hasLength(prefix) ? prefix.length() : 0,migrationName.length() - suffix.length());
    }
  }
  return migrationName;
}
