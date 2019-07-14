/** 
 * Returns the max allowed concurrent executions.
 */
int maxConcurrentExecutions(){
  if (circuit.getSuccessThreshold() != null)   return circuit.getSuccessThreshold().getDenominator();
 else   if (circuit.getFailureThreshold() != null)   return circuit.getFailureThreshold().getDenominator();
 else   return 1;
}
