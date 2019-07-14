@Override protected void doExecute(Flyway flyway){
  MigrationInfoService info=flyway.info();
  MigrationInfo current=info.current();
  MigrationVersion currentSchemaVersion=current == null ? MigrationVersion.EMPTY : current.getVersion();
  log.info("Schema version: " + currentSchemaVersion);
  log.info("");
  log.info(MigrationInfoDumper.dumpToAsciiTable(info.all()));
}
