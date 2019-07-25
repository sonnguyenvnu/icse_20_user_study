@NotNull @Override protected AbstractInstaller.State install(boolean dryRun){
  if (!(PluginUtil.isGitPluginEnabled())) {
    return AbstractInstaller.State.NOT_ENABLED;
  }
  try {
    String currentValue=GitConfigUtil.getValue(myProject,myProject.getBaseDir(),GitGlobalConfigFixesInstaller.CORE_AUTOCRLF);
    if (getCoreAutocrlfValue().equals(currentValue)) {
      return AbstractInstaller.State.INSTALLED;
    }
  }
 catch (  VcsException e) {
    if (!(dryRun)) {
      if (LOG.isEnabledFor(Level.WARN)) {
        LOG.warn("Can't get value",e);
      }
    }
    return AbstractInstaller.State.NOT_INSTALLED;
  }
  if (dryRun) {
    return AbstractInstaller.State.NOT_INSTALLED;
  }
  try {
    setGlobalProperty(myProject,CORE_AUTOCRLF,getCoreAutocrlfValue());
    return AbstractInstaller.State.INSTALLED;
  }
 catch (  VcsException e) {
    if (LOG.isEnabledFor(Level.WARN)) {
      LOG.warn("Can't set value",e);
    }
    Messages.showErrorDialog(myProject,"Can't set Git global property: " + e.getMessage(),"Git Global property");
    return AbstractInstaller.State.NOT_INSTALLED;
  }
}
