private boolean matchOS(final String osNamePrefix){
  if (OS_NAME == null) {
    return false;
  }
  return OS_NAME.startsWith(osNamePrefix);
}
