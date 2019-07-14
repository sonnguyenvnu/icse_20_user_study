@Override protected Object run(Flyway flyway){
  MigrationInfoService info=flyway.info();
  MigrationInfo current=info.current();
  MigrationVersion currentSchemaVersion=current == null ? MigrationVersion.EMPTY : current.getVersion();
  System.out.println("Schema version: " + currentSchemaVersion);
  System.out.println(MigrationInfoDumper.dumpToAsciiTable(info.all()));
  return null;
}
