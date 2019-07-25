@Override protected void run(Bootstrap<KeywhizConfig> bootstrap,Namespace namespace,KeywhizConfig config) throws Exception {
  if (!config.getEnvironment().equals("development")) {
    throw new IllegalArgumentException("cannot call db-seed in non-development environment");
  }
  DataSource dataSource=config.getDataSourceFactory().build(new MetricRegistry(),"db-seed-datasource");
  DSLContext dslContext=DSLContexts.databaseAgnostic(dataSource);
  doImport(dslContext);
}
