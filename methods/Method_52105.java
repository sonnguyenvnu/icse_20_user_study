private void buildGuardStatementMap(List<String> logLevels,List<String> guardMethods){
  for (int i=0; i < logLevels.size(); i++) {
    String logLevel=logLevels.get(i);
    if (guardStmtByLogLevel.containsKey(logLevel)) {
      String combinedGuard=guardStmtByLogLevel.get(logLevel);
      combinedGuard+="|" + guardMethods.get(i);
      guardStmtByLogLevel.put(logLevel,combinedGuard);
    }
 else {
      guardStmtByLogLevel.put(logLevel,guardMethods.get(i));
    }
  }
}
