private void addMigrations(List<ResolvedMigration> migrations,String prefix,String separator,String[] suffixes,boolean repeatable){
  for (  LoadableResource resource : resourceProvider.getResources(prefix,suffixes)) {
    String filename=resource.getFilename();
    if (isSqlCallback(filename,separator,suffixes)) {
      continue;
    }
    SqlScript sqlScript=sqlScriptFactory.createSqlScript(resource,configuration.isMixed());
    int checksum;
    checksum=resource.checksum();
    Pair<MigrationVersion,String> info=MigrationInfoHelper.extractVersionAndDescription(filename,prefix,separator,suffixes,repeatable);
    migrations.add(new ResolvedMigrationImpl(info.getLeft(),info.getRight(),resource.getRelativePath(),checksum,MigrationType.SQL,resource.getAbsolutePathOnDisk(),new SqlMigrationExecutor(sqlScriptExecutorFactory,sqlScript)){
      @Override public void validate(){
      }
    }
);
  }
}
