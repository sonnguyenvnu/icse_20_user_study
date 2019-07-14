private static String steps(PolicyStats policyStats){
  long operations=policyStats.operationCount();
  long complexity=(long)(100 * policyStats.complexity());
  return (operations == 0) ? "?" : String.format("%,d (%,d %%)",operations,complexity);
}
