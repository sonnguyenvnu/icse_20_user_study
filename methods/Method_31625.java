@Override public List<ResolvedMigration> resolveMigrations(Context context){
  List<ResolvedMigration> migrations=new ArrayList<>();
  for (  Class<?> clazz : classProvider.getClasses(JavaMigration.class)) {
    JavaMigration javaMigration=ClassUtils.instantiate(clazz.getName(),configuration.getClassLoader());
    migrations.add(new ResolvedJavaMigration(javaMigration));
  }
  Collections.sort(migrations,new ResolvedMigrationComparator());
  return migrations;
}
