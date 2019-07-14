@Override protected void doExecute(Flyway flyway){
  flyway.undo();
  MigrationInfo current=flyway.info().current();
  if (current != null && current.getVersion() != null) {
    mavenProject.getProperties().setProperty("flyway.current",current.getVersion().toString());
  }
}
