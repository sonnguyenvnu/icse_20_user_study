public static boolean isServiceProKey(String key){
  return !StringUtils.isBlank(key) && (key.startsWith(SERVICE_CONSUMER_PROPERTY_KEY_PREFIX) || key.startsWith(SERVICE_PROVIDER_PROPERTY_KEY_PREFIX));
}
