/** 
 * Checks to determine if a threshold has been met and the circuit should be opened or closed. <p> If a success ratio is configured, the circuit is opened or closed after the expected number of executions based on whether the ratio was exceeded. <p> Else if a failure ratio is configured, the circuit is opened or closed after the expected number of executions based on whether the ratio was not exceeded. <p> Else when no thresholds are configured, the circuit opens or closes on a single failure or success.
 */
synchronized void checkThreshold(){
  Ratio successRatio=circuit.getSuccessThreshold();
  Ratio failureRatio=circuit.getFailureThreshold();
  if (successRatio != null) {
    if (bitSet.occupiedBits() == successRatio.getDenominator() || (successRatio.getValue() == 1.0 && bitSet.positiveRatioValue() < 1.0))     if (bitSet.positiveRatioValue() >= successRatio.getValue())     circuit.close();
 else     circuit.open();
  }
 else   if (failureRatio != null) {
    if (bitSet.occupiedBits() == failureRatio.getDenominator() || (failureRatio.getValue() == 1.0 && bitSet.negativeRatioValue() < 1.0))     if (bitSet.negativeRatioValue() >= failureRatio.getValue())     circuit.open();
 else     circuit.close();
  }
 else {
    if (bitSet.positiveRatioValue() == 1)     circuit.close();
 else     circuit.open();
  }
}
