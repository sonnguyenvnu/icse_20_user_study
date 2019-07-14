public static String getUserLogName(String identifier){
  Preconditions.checkArgument(StringUtils.isNotBlank(identifier));
  return USER_LOG_PREFIX + identifier;
}
