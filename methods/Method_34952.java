public PostgresWebCrawler newInstance() throws Exception {
  return new PostgresWebCrawler(new PostgresDBServiceImpl(comboPooledDataSource));
}
