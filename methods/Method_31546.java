/** 
 * Detects a fallback user in case this one is missing.
 * @param user The user to check.
 * @return The user to use.
 */
private String detectFallbackUser(String user){
  if (!StringUtils.hasText(user)) {
    String boxfuseDatabaseUser=System.getenv("BOXFUSE_DATABASE_USER");
    if (StringUtils.hasText(boxfuseDatabaseUser)) {
      return boxfuseDatabaseUser;
    }
  }
  return user;
}
