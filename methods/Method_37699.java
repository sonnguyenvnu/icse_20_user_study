private boolean matchOS(final String osNamePrefix,final String osVersionPrefix){
  if ((OS_NAME == null) || (OS_VERSION == null)) {
    return false;
  }
  return OS_NAME.startsWith(osNamePrefix) && OS_VERSION.startsWith(osVersionPrefix);
}
