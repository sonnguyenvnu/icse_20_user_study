/** 
 * Returns whether this  {@link OpenLocationCode} is a padded Open Location Code, meaning that itcontains less than 8 valid digits.
 * @return True if this code is padded.
 */
private boolean isPadded(){
  return code.indexOf(PADDING_CHARACTER) >= 0;
}
