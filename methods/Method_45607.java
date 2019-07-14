public static boolean isMethodProKey(String key){
  return !StringUtils.isBlank(key) && (key.startsWith(METHOD_CONSUMER_PROPERTY_KEY_PREFIX) || key.startsWith(METHOD_PROVIDER_PROPERTY_KEY_PREFIX));
}
