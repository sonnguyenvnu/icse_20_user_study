@Override protected void doExecute(Flyway flyway) throws Exception {
  flyway.migrate();
  MigrationInfo current=flyway.info().current();
  if (current != null && current.getVersion() != null) {
    mavenProject.getProperties().setProperty("flyway.current",current.getVersion().toString());
  }
}
