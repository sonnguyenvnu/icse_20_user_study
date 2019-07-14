/** 
 * Detects a fallback url in case this one is missing.
 * @param url The url to check.
 * @return The url to use.
 */
private String detectFallbackUrl(String url){
  if (!StringUtils.hasText(url)) {
    String boxfuseDatabaseUrl=System.getenv("BOXFUSE_DATABASE_URL");
    if (StringUtils.hasText(boxfuseDatabaseUrl)) {
      return boxfuseDatabaseUrl;
    }
    throw new FlywayException("Missing required JDBC URL. Unable to create DataSource!");
  }
  if (!url.toLowerCase().startsWith("jdbc:")) {
    throw new FlywayException("Invalid JDBC URL (should start with jdbc:) : " + url);
  }
  return url;
}
