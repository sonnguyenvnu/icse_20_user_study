public final String getBaselineStatement(Table table){
  return String.format(getInsertStatement(table).replace("?","%s"),1,"'" + configuration.getBaselineVersion() + "'","'" + AbbreviationUtils.abbreviateDescription(configuration.getBaselineDescription()) + "'","'" + MigrationType.BASELINE + "'","'" + AbbreviationUtils.abbreviateScript(configuration.getBaselineDescription()) + "'","NULL","'" + installedBy + "'",0,getBooleanTrue());
}
