/** 
 * Configures Flyway with these properties. This overwrites any existing configuration. Property names are documented in the flyway maven plugin. <p>To use a custom ClassLoader, it must be passed to the Flyway constructor prior to calling this method.</p>
 * @param props Properties used for configuration.
 * @throws FlywayException when the configuration failed.
 */
public void configure(Map<String,String> props){
  props=new HashMap<>(props);
  String driverProp=props.remove(ConfigUtils.DRIVER);
  if (driverProp != null) {
    dataSource=null;
    driver=driverProp;
  }
  String urlProp=props.remove(ConfigUtils.URL);
  if (urlProp != null) {
    dataSource=null;
    url=urlProp;
  }
  String userProp=props.remove(ConfigUtils.USER);
  if (userProp != null) {
    dataSource=null;
    user=userProp;
  }
  String passwordProp=props.remove(ConfigUtils.PASSWORD);
  if (passwordProp != null) {
    dataSource=null;
    password=passwordProp;
  }
  if (StringUtils.hasText(url) && (StringUtils.hasText(urlProp) || StringUtils.hasText(driverProp) || StringUtils.hasText(userProp) || StringUtils.hasText(passwordProp))) {
    setDataSource(new DriverDataSource(classLoader,driver,url,user,password));
  }
  Integer connectRetriesProp=getIntegerProp(props,ConfigUtils.CONNECT_RETRIES);
  if (connectRetriesProp != null) {
    setConnectRetries(connectRetriesProp);
  }
  String initSqlProp=props.remove(ConfigUtils.INIT_SQL);
  if (initSqlProp != null) {
    setInitSql(initSqlProp);
  }
  String locationsProp=props.remove(ConfigUtils.LOCATIONS);
  if (locationsProp != null) {
    setLocationsAsStrings(StringUtils.tokenizeToStringArray(locationsProp,","));
  }
  Boolean placeholderReplacementProp=getBooleanProp(props,ConfigUtils.PLACEHOLDER_REPLACEMENT);
  if (placeholderReplacementProp != null) {
    setPlaceholderReplacement(placeholderReplacementProp);
  }
  String placeholderPrefixProp=props.remove(ConfigUtils.PLACEHOLDER_PREFIX);
  if (placeholderPrefixProp != null) {
    setPlaceholderPrefix(placeholderPrefixProp);
  }
  String placeholderSuffixProp=props.remove(ConfigUtils.PLACEHOLDER_SUFFIX);
  if (placeholderSuffixProp != null) {
    setPlaceholderSuffix(placeholderSuffixProp);
  }
  String sqlMigrationPrefixProp=props.remove(ConfigUtils.SQL_MIGRATION_PREFIX);
  if (sqlMigrationPrefixProp != null) {
    setSqlMigrationPrefix(sqlMigrationPrefixProp);
  }
  String undoSqlMigrationPrefixProp=props.remove(ConfigUtils.UNDO_SQL_MIGRATION_PREFIX);
  if (undoSqlMigrationPrefixProp != null) {
    setUndoSqlMigrationPrefix(undoSqlMigrationPrefixProp);
  }
  String repeatableSqlMigrationPrefixProp=props.remove(ConfigUtils.REPEATABLE_SQL_MIGRATION_PREFIX);
  if (repeatableSqlMigrationPrefixProp != null) {
    setRepeatableSqlMigrationPrefix(repeatableSqlMigrationPrefixProp);
  }
  String sqlMigrationSeparatorProp=props.remove(ConfigUtils.SQL_MIGRATION_SEPARATOR);
  if (sqlMigrationSeparatorProp != null) {
    setSqlMigrationSeparator(sqlMigrationSeparatorProp);
  }
  String sqlMigrationSuffixesProp=props.remove(ConfigUtils.SQL_MIGRATION_SUFFIXES);
  if (sqlMigrationSuffixesProp != null) {
    setSqlMigrationSuffixes(StringUtils.tokenizeToStringArray(sqlMigrationSuffixesProp,","));
  }
  String encodingProp=props.remove(ConfigUtils.ENCODING);
  if (encodingProp != null) {
    setEncodingAsString(encodingProp);
  }
  String schemasProp=props.remove(ConfigUtils.SCHEMAS);
  if (schemasProp != null) {
    setSchemas(StringUtils.tokenizeToStringArray(schemasProp,","));
  }
  String tableProp=props.remove(ConfigUtils.TABLE);
  if (tableProp != null) {
    setTable(tableProp);
  }
  String tablespaceProp=props.remove(ConfigUtils.TABLESPACE);
  if (tablespaceProp != null) {
    setTablespace(tablespaceProp);
  }
  Boolean cleanOnValidationErrorProp=getBooleanProp(props,ConfigUtils.CLEAN_ON_VALIDATION_ERROR);
  if (cleanOnValidationErrorProp != null) {
    setCleanOnValidationError(cleanOnValidationErrorProp);
  }
  Boolean cleanDisabledProp=getBooleanProp(props,ConfigUtils.CLEAN_DISABLED);
  if (cleanDisabledProp != null) {
    setCleanDisabled(cleanDisabledProp);
  }
  Boolean validateOnMigrateProp=getBooleanProp(props,ConfigUtils.VALIDATE_ON_MIGRATE);
  if (validateOnMigrateProp != null) {
    setValidateOnMigrate(validateOnMigrateProp);
  }
  String baselineVersionProp=props.remove(ConfigUtils.BASELINE_VERSION);
  if (baselineVersionProp != null) {
    setBaselineVersion(MigrationVersion.fromVersion(baselineVersionProp));
  }
  String baselineDescriptionProp=props.remove(ConfigUtils.BASELINE_DESCRIPTION);
  if (baselineDescriptionProp != null) {
    setBaselineDescription(baselineDescriptionProp);
  }
  Boolean baselineOnMigrateProp=getBooleanProp(props,ConfigUtils.BASELINE_ON_MIGRATE);
  if (baselineOnMigrateProp != null) {
    setBaselineOnMigrate(baselineOnMigrateProp);
  }
  Boolean ignoreMissingMigrationsProp=getBooleanProp(props,ConfigUtils.IGNORE_MISSING_MIGRATIONS);
  if (ignoreMissingMigrationsProp != null) {
    setIgnoreMissingMigrations(ignoreMissingMigrationsProp);
  }
  Boolean ignoreIgnoredMigrationsProp=getBooleanProp(props,ConfigUtils.IGNORE_IGNORED_MIGRATIONS);
  if (ignoreIgnoredMigrationsProp != null) {
    setIgnoreIgnoredMigrations(ignoreIgnoredMigrationsProp);
  }
  Boolean ignorePendingMigrationsProp=getBooleanProp(props,ConfigUtils.IGNORE_PENDING_MIGRATIONS);
  if (ignorePendingMigrationsProp != null) {
    setIgnorePendingMigrations(ignorePendingMigrationsProp);
  }
  Boolean ignoreFutureMigrationsProp=getBooleanProp(props,ConfigUtils.IGNORE_FUTURE_MIGRATIONS);
  if (ignoreFutureMigrationsProp != null) {
    setIgnoreFutureMigrations(ignoreFutureMigrationsProp);
  }
  String targetProp=props.remove(ConfigUtils.TARGET);
  if (targetProp != null) {
    setTarget(MigrationVersion.fromVersion(targetProp));
  }
  Boolean outOfOrderProp=getBooleanProp(props,ConfigUtils.OUT_OF_ORDER);
  if (outOfOrderProp != null) {
    setOutOfOrder(outOfOrderProp);
  }
  String resolversProp=props.remove(ConfigUtils.RESOLVERS);
  if (StringUtils.hasLength(resolversProp)) {
    setResolversAsClassNames(StringUtils.tokenizeToStringArray(resolversProp,","));
  }
  Boolean skipDefaultResolversProp=getBooleanProp(props,ConfigUtils.SKIP_DEFAULT_RESOLVERS);
  if (skipDefaultResolversProp != null) {
    setSkipDefaultResolvers(skipDefaultResolversProp);
  }
  String callbacksProp=props.remove(ConfigUtils.CALLBACKS);
  if (StringUtils.hasLength(callbacksProp)) {
    setCallbacksAsClassNames(StringUtils.tokenizeToStringArray(callbacksProp,","));
  }
  Boolean skipDefaultCallbacksProp=getBooleanProp(props,ConfigUtils.SKIP_DEFAULT_CALLBACKS);
  if (skipDefaultCallbacksProp != null) {
    setSkipDefaultCallbacks(skipDefaultCallbacksProp);
  }
  Map<String,String> placeholdersFromProps=new HashMap<>(getPlaceholders());
  Iterator<Map.Entry<String,String>> iterator=props.entrySet().iterator();
  while (iterator.hasNext()) {
    Map.Entry<String,String> entry=iterator.next();
    String propertyName=entry.getKey();
    if (propertyName.startsWith(ConfigUtils.PLACEHOLDERS_PROPERTY_PREFIX) && propertyName.length() > ConfigUtils.PLACEHOLDERS_PROPERTY_PREFIX.length()) {
      String placeholderName=propertyName.substring(ConfigUtils.PLACEHOLDERS_PROPERTY_PREFIX.length());
      String placeholderValue=entry.getValue();
      placeholdersFromProps.put(placeholderName,placeholderValue);
      iterator.remove();
    }
  }
  setPlaceholders(placeholdersFromProps);
  Boolean mixedProp=getBooleanProp(props,ConfigUtils.MIXED);
  if (mixedProp != null) {
    setMixed(mixedProp);
  }
  Boolean groupProp=getBooleanProp(props,ConfigUtils.GROUP);
  if (groupProp != null) {
    setGroup(groupProp);
  }
  String installedByProp=props.remove(ConfigUtils.INSTALLED_BY);
  if (installedByProp != null) {
    setInstalledBy(installedByProp);
  }
  String dryRunOutputProp=props.remove(ConfigUtils.DRYRUN_OUTPUT);
  if (dryRunOutputProp != null) {
    setDryRunOutputAsFileName(dryRunOutputProp);
  }
  String errorOverridesProp=props.remove(ConfigUtils.ERROR_OVERRIDES);
  if (errorOverridesProp != null) {
    setErrorOverrides(StringUtils.tokenizeToStringArray(errorOverridesProp,","));
  }
  Boolean streamProp=getBooleanProp(props,ConfigUtils.STREAM);
  if (streamProp != null) {
    setStream(streamProp);
  }
  Boolean batchProp=getBooleanProp(props,ConfigUtils.BATCH);
  if (batchProp != null) {
    setBatch(batchProp);
  }
  Boolean oracleSqlplusProp=getBooleanProp(props,ConfigUtils.ORACLE_SQLPLUS);
  if (oracleSqlplusProp != null) {
    setOracleSqlplus(oracleSqlplusProp);
  }
  Boolean oracleSqlplusWarnProp=getBooleanProp(props,ConfigUtils.ORACLE_SQLPLUS_WARN);
  if (oracleSqlplusWarnProp != null) {
    setOracleSqlplusWarn(oracleSqlplusWarnProp);
  }
  String licenseKeyProp=props.remove(ConfigUtils.LICENSE_KEY);
  if (licenseKeyProp != null) {
    setLicenseKey(licenseKeyProp);
  }
  for (  String key : props.keySet()) {
    if (key.startsWith("flyway.")) {
      throw new FlywayException("Unknown configuration property: " + key);
    }
  }
}
