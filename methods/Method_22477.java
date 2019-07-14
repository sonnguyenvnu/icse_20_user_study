static private void installOnStartUp(final Base base,final AvailableContribution availableContrib){
  if (availableContrib.link == null) {
    Messages.showWarning(Language.interpolate("contrib.errors.update_on_restart_failed",availableContrib.getName()),Language.text("contrib.unsupported_operating_system"));
  }
 else {
    try {
      URL downloadUrl=new URL(availableContrib.link);
      ContributionManager.downloadAndInstallOnStartup(base,downloadUrl,availableContrib);
    }
 catch (    MalformedURLException e) {
      Messages.showWarning(Language.interpolate("contrib.errors.update_on_restart_failed",availableContrib.getName()),Language.text("contrib.errors.malformed_url"),e);
    }
  }
}
