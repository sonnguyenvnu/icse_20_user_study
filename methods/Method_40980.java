public void initialize(){
  try {
    this.workManager=(WorkManager)new InitialContext().lookup(workManagerName);
  }
 catch (  NamingException e) {
    throw new IllegalStateException("Could not locate WorkManager: " + e.getMessage(),e);
  }
}
