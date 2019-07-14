/** 
 * Checks to determine if a threshold has been met and the circuit should be opened or closed. <p> When a failure ratio is configured, the circuit is opened after the expected number of executions based on whether the ratio was exceeded. <p> If a failure threshold is configured, the circuit is opened if the expected number of executions fails else it's closed if a single execution succeeds.
 */
synchronized void checkThreshold(){
  Ratio failureRatio=circuit.getFailureThreshold();
  if (failureRatio != null && bitSet.occupiedBits() >= failureRatio.getDenominator() && bitSet.negativeRatioValue() >= failureRatio.getValue())   circuit.open();
 else   if (failureRatio == null && bitSet.negativeRatioValue() == 1)   circuit.open();
}
