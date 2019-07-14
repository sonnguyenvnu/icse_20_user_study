/** 
 * Gets total character count.
 * @return total character count
 */
public int getTotalCharacterCount(){
  return langPropsService.get("characters").length();
}
