@Override public void start(@Nonnull final String username){
  if (!getProcessByUsername(username).isPresent()) {
    final SparkLauncherSparkShellProcess process;
    try {
      process=createProcessBuilder(username).build();
      process.addListener(this);
      process.setUsername(username);
      users.put(process.getClientId(),process.getClientSecret());
    }
 catch (    final IOException e) {
      log.error("Failed to start Spark Shell process",e);
      throw new IllegalStateException("Failed to start Spark Shell process",e);
    }
    setProcessForUser(username,process);
    listeners.forEach(listener -> listener.processStarted(process));
  }
}
