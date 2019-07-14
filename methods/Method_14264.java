/** 
 * Checks if a given string has a comma as the last character. This is primarily used to detect edge cases like doing range("1,").
 */
private boolean hasCommaAsLastCharacter(String test){
  Matcher lastCharacterCommaMatcher=lastCharacterCommaPattern.matcher(test);
  return lastCharacterCommaMatcher.find();
}
