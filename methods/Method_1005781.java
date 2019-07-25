@Nullable public static Configuration useful(@Nullable Configuration configuration){
  if (configuration != null && configuration.isCanBeResolved()) {
    return configuration;
  }
  return null;
}
