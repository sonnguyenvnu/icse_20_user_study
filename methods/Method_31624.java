@Override public List<ResolvedMigration> resolveMigrations(Context context){
  List<ResolvedMigration> migrations=new ArrayList<>();
  for (  JavaMigration javaMigration : javaMigrations) {
    migrations.add(new ResolvedJavaMigration(javaMigration));
  }
  Collections.sort(migrations,new ResolvedMigrationComparator());
  return migrations;
}
