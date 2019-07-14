/** 
 * verify 
 */
private void verifyPriceWindow(String priceWindow){
  Assert.isTrue(BitcoiniumUtils.isValidPriceWindow(priceWindow),priceWindow + " is not a valid price window!");
}
