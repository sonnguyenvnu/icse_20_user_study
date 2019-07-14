private static String convertKey(String key){
  if ("FLYWAY_BASELINE_DESCRIPTION".equals(key)) {
    return BASELINE_DESCRIPTION;
  }
  if ("FLYWAY_BASELINE_ON_MIGRATE".equals(key)) {
    return BASELINE_ON_MIGRATE;
  }
  if ("FLYWAY_BASELINE_VERSION".equals(key)) {
    return BASELINE_VERSION;
  }
  if ("FLYWAY_BATCH".equals(key)) {
    return BATCH;
  }
  if ("FLYWAY_CALLBACKS".equals(key)) {
    return CALLBACKS;
  }
  if ("FLYWAY_CLEAN_DISABLED".equals(key)) {
    return CLEAN_DISABLED;
  }
  if ("FLYWAY_CLEAN_ON_VALIDATION_ERROR".equals(key)) {
    return CLEAN_ON_VALIDATION_ERROR;
  }
  if ("FLYWAY_CONFIG_FILE_ENCODING".equals(key)) {
    return CONFIG_FILE_ENCODING;
  }
  if ("FLYWAY_CONFIG_FILES".equals(key)) {
    return CONFIG_FILES;
  }
  if ("FLYWAY_CONNECT_RETRIES".equals(key)) {
    return CONNECT_RETRIES;
  }
  if ("FLYWAY_DRIVER".equals(key)) {
    return DRIVER;
  }
  if ("FLYWAY_DRYRUN_OUTPUT".equals(key)) {
    return DRYRUN_OUTPUT;
  }
  if ("FLYWAY_ENCODING".equals(key)) {
    return ENCODING;
  }
  if ("FLYWAY_ERROR_OVERRIDES".equals(key)) {
    return ERROR_OVERRIDES;
  }
  if ("FLYWAY_GROUP".equals(key)) {
    return GROUP;
  }
  if ("FLYWAY_IGNORE_FUTURE_MIGRATIONS".equals(key)) {
    return IGNORE_FUTURE_MIGRATIONS;
  }
  if ("FLYWAY_IGNORE_MISSING_MIGRATIONS".equals(key)) {
    return IGNORE_MISSING_MIGRATIONS;
  }
  if ("FLYWAY_IGNORE_IGNORED_MIGRATIONS".equals(key)) {
    return IGNORE_IGNORED_MIGRATIONS;
  }
  if ("FLYWAY_IGNORE_PENDING_MIGRATIONS".equals(key)) {
    return IGNORE_PENDING_MIGRATIONS;
  }
  if ("FLYWAY_INIT_SQL".equals(key)) {
    return INIT_SQL;
  }
  if ("FLYWAY_INSTALLED_BY".equals(key)) {
    return INSTALLED_BY;
  }
  if ("FLYWAY_LICENSE_KEY".equals(key)) {
    return LICENSE_KEY;
  }
  if ("FLYWAY_LOCATIONS".equals(key)) {
    return LOCATIONS;
  }
  if ("FLYWAY_MIXED".equals(key)) {
    return MIXED;
  }
  if ("FLYWAY_OUT_OF_ORDER".equals(key)) {
    return OUT_OF_ORDER;
  }
  if ("FLYWAY_PASSWORD".equals(key)) {
    return PASSWORD;
  }
  if ("FLYWAY_PLACEHOLDER_PREFIX".equals(key)) {
    return PLACEHOLDER_PREFIX;
  }
  if ("FLYWAY_PLACEHOLDER_REPLACEMENT".equals(key)) {
    return PLACEHOLDER_REPLACEMENT;
  }
  if ("FLYWAY_PLACEHOLDER_SUFFIX".equals(key)) {
    return PLACEHOLDER_SUFFIX;
  }
  if (key.matches("FLYWAY_PLACEHOLDERS_.+")) {
    return PLACEHOLDERS_PROPERTY_PREFIX + key.substring("FLYWAY_PLACEHOLDERS_".length()).toLowerCase(Locale.ENGLISH);
  }
  if ("FLYWAY_REPEATABLE_SQL_MIGRATION_PREFIX".equals(key)) {
    return REPEATABLE_SQL_MIGRATION_PREFIX;
  }
  if ("FLYWAY_RESOLVERS".equals(key)) {
    return RESOLVERS;
  }
  if ("FLYWAY_SCHEMAS".equals(key)) {
    return SCHEMAS;
  }
  if ("FLYWAY_SKIP_DEFAULT_CALLBACKS".equals(key)) {
    return SKIP_DEFAULT_CALLBACKS;
  }
  if ("FLYWAY_SKIP_DEFAULT_RESOLVERS".equals(key)) {
    return SKIP_DEFAULT_RESOLVERS;
  }
  if ("FLYWAY_SQL_MIGRATION_PREFIX".equals(key)) {
    return SQL_MIGRATION_PREFIX;
  }
  if ("FLYWAY_SQL_MIGRATION_SEPARATOR".equals(key)) {
    return SQL_MIGRATION_SEPARATOR;
  }
  if ("FLYWAY_SQL_MIGRATION_SUFFIXES".equals(key)) {
    return SQL_MIGRATION_SUFFIXES;
  }
  if ("FLYWAY_STREAM".equals(key)) {
    return STREAM;
  }
  if ("FLYWAY_TABLE".equals(key)) {
    return TABLE;
  }
  if ("FLYWAY_TABLESPACE".equals(key)) {
    return TABLESPACE;
  }
  if ("FLYWAY_TARGET".equals(key)) {
    return TARGET;
  }
  if ("FLYWAY_UNDO_SQL_MIGRATION_PREFIX".equals(key)) {
    return UNDO_SQL_MIGRATION_PREFIX;
  }
  if ("FLYWAY_URL".equals(key)) {
    return URL;
  }
  if ("FLYWAY_USER".equals(key)) {
    return USER;
  }
  if ("FLYWAY_VALIDATE_ON_MIGRATE".equals(key)) {
    return VALIDATE_ON_MIGRATE;
  }
  if ("FLYWAY_ORACLE_SQLPLUS".equals(key)) {
    return ORACLE_SQLPLUS;
  }
  if ("FLYWAY_ORACLE_SQLPLUS_WARN".equals(key)) {
    return ORACLE_SQLPLUS_WARN;
  }
  if ("FLYWAY_JAR_DIRS".equals(key)) {
    return JAR_DIRS;
  }
  if ("FLYWAY_CONFIGURATIONS".equals(key)) {
    return CONFIGURATIONS;
  }
  return null;
}
