@PostConstruct public void init(){
  this.logLevel=LogLevel.level(this.accessLogLevel);
  this.formatFields=LoggingUtil.extractTokens(LogFields.class,this.accessLogFormat);
  this.logMessage=LoggingUtil.toLogMessage(this.accessLogFormat);
  if (StringUtils.isBlank(ignoreUsersCsv)) {
    this.ignoreUsers=Collections.emptySet();
  }
 else {
    this.ignoreUsers=Arrays.stream(this.ignoreUsersCsv.split(",")).map(String::trim).map(UsernamePrincipal::new).collect(Collectors.toSet());
  }
  if (StringUtils.isBlank(ignoreGroupsCsv)) {
    this.ignoreGroups=Collections.emptySet();
  }
 else {
    this.ignoreGroups=Arrays.stream(this.ignoreGroupsCsv.split(",")).map(String::trim).map(GroupPrincipal::new).collect(Collectors.toSet());
  }
}
