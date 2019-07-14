protected String filenameFrom(DataSource dataSource){
  return dataSource.getNiceFileName(configuration.isReportShortNames(),configuration.getInputPaths());
}
