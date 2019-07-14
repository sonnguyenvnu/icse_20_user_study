/** 
 * Gets the equivalent object that was created with the "commonly used" code. 
 */
public Currency getCommonlyUsedCurrency(){
  return getCodeCurrency(attributes.commonCode);
}
