@Override public void register(@Nonnull final String clientId,@Nonnull final RegistrationRequest registration){
  final Optional<SparkLauncherSparkShellProcess> clientProcess=getProcessByClientId(clientId);
  if (clientProcess.isPresent()) {
    clientProcess.get().setHostname(registration.getHost());
    clientProcess.get().setPort(registration.getPort());
    clientProcess.get().setReady(true);
  }
 else   if (systemProcess != null && clientId.equals(systemProcess.getClientId())) {
    systemProcess.setHostname(registration.getHost());
    systemProcess.setPort(registration.getPort());
    systemProcess.setReady(true);
  }
 else {
    log.warn("Tried to register unknown Spark Shell client: {}",clientId);
  }
}
