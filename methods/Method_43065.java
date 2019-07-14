/** 
 * verify 
 */
private void verifyTimeWindow(String timeWindow){
  Assert.isTrue(BitcoiniumUtils.isValidTimeWindow(timeWindow),timeWindow + " is not a valid time window!");
}
