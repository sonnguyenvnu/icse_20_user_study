/** 
 * Load username password from settings
 * @throws FlywayException when the credentials could not be loaded.
 */
private void loadCredentialsFromSettings() throws FlywayException {
  final Server server=settings.getServer(serverId);
  if (user == null) {
    if (server != null) {
      user=server.getUsername();
      SettingsDecryptionResult result=settingsDecrypter.decrypt(new DefaultSettingsDecryptionRequest(server));
      for (      SettingsProblem problem : result.getProblems()) {
        if (problem.getSeverity() == SettingsProblem.Severity.ERROR || problem.getSeverity() == SettingsProblem.Severity.FATAL) {
          throw new FlywayException("Unable to decrypt password: " + problem,problem.getException());
        }
      }
      password=result.getServer().getPassword();
    }
  }
 else   if (server != null) {
    throw new FlywayException("You specified credentials both in the Flyway config and settings.xml. Use either one or the other");
  }
}
