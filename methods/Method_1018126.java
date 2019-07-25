@Override public void start(@Nonnull String username){
  SparkShellProcess sparkProcess=getProcessForUser(username);
  if (sparkProcess instanceof SparkLivyProcess) {
    start((SparkLivyProcess)sparkProcess);
  }
}
