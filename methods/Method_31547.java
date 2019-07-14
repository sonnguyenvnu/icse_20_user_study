/** 
 * Detects a fallback password in case this one is missing.
 * @param password The password to check.
 * @return The password to use.
 */
private String detectFallbackPassword(String password){
  if (!StringUtils.hasText(password)) {
    String boxfuseDatabasePassword=System.getenv("BOXFUSE_DATABASE_PASSWORD");
    if (StringUtils.hasText(boxfuseDatabasePassword)) {
      return boxfuseDatabasePassword;
    }
  }
  return password;
}
