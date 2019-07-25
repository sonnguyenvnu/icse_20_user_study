/** 
 * Translates this document range by a given amount.
 * @param amount The amount to translate this range by.
 * @return This (modified) range.
 */
public DocumentRange translate(int amount){
  startOffs+=amount;
  endOffs+=amount;
  return this;
}
