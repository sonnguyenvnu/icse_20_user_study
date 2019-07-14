/** 
 * Checks if the printer is non null and a provider.
 * @param printer  the printer to check
 */
private void checkPrinter(DateTimePrinter printer){
  if (printer == null) {
    throw new IllegalArgumentException("No printer supplied");
  }
}
