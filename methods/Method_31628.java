public List<ResolvedMigration> resolveMigrations(Context context){
  List<ResolvedMigration> migrations=new ArrayList<>();
  String separator=configuration.getSqlMigrationSeparator();
  String[] suffixes=configuration.getSqlMigrationSuffixes();
  addMigrations(migrations,configuration.getSqlMigrationPrefix(),separator,suffixes,false);
  addMigrations(migrations,configuration.getRepeatableSqlMigrationPrefix(),separator,suffixes,true);
  Collections.sort(migrations,new ResolvedMigrationComparator());
  return migrations;
}
