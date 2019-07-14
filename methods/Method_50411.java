/** 
 * Build redis key prefix string.
 * @param applicationName the application name
 * @return the string
 */
public static String buildRedisKeyPrefix(final String applicationName){
  return String.format(CommonConstant.RECOVER_REDIS_KEY_PRE,applicationName);
}
