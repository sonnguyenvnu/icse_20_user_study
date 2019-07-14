@Override public void preCheck(String dataSourceName){
  super.preCheck(dataSourceName);
  try {
    this.setFile(ResourceUtils.getFile(StringUtils.trimAllWhitespace(this.getFile())).getAbsolutePath());
  }
 catch (  IOException e) {
    throw new RuntimeException("[Sentinel Starter] DataSource " + dataSourceName + " handle file [" + this.getFile() + "] error: " + e.getMessage(),e);
  }
}
