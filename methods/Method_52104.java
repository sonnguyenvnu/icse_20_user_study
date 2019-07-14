private void extractProperties(){
  if (guardStmtByLogLevel.isEmpty()) {
    List<String> logLevels=new ArrayList<>(super.getProperty(LOG_LEVELS));
    List<String> guardMethods=new ArrayList<>(super.getProperty(GUARD_METHODS));
    if (guardMethods.isEmpty() && !logLevels.isEmpty()) {
      throw new IllegalArgumentException("Can't specify logLevels without specifying guardMethods.");
    }
    if (logLevels.size() > guardMethods.size()) {
      int needed=logLevels.size() - guardMethods.size();
      String lastGuard=guardMethods.get(guardMethods.size() - 1);
      for (int i=0; i < needed; i++) {
        guardMethods.add(lastGuard);
      }
    }
    if (logLevels.size() != guardMethods.size()) {
      throw new IllegalArgumentException("For each logLevel a guardMethod must be specified.");
    }
    buildGuardStatementMap(logLevels,guardMethods);
  }
}
