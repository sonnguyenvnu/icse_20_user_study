public final String getInstalledBy(){
  if (installedBy == null) {
    installedBy=configuration.getInstalledBy() == null ? getCurrentUser() : configuration.getInstalledBy();
  }
  return installedBy;
}
