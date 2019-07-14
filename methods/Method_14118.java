/** 
 * Tests if character is a vowel
 * @param inChar character to be tested in string to be encoded
 * @return true if character is a vowel, false if not
 */
boolean IsVowel(char inChar){
  if ((inChar == 'A') || (inChar == 'E') || (inChar == 'I') || (inChar == 'O') || (inChar == 'U') || (inChar == 'Y') || (inChar == 'À') || (inChar == 'Á') || (inChar == 'Â') || (inChar == 'Ã') || (inChar == 'Ä') || (inChar == 'Å') || (inChar == 'Æ') || (inChar == 'È') || (inChar == 'É') || (inChar == 'Ê') || (inChar == 'Ë') || (inChar == 'Ì') || (inChar == 'Í') || (inChar == 'Î') || (inChar == 'Ï') || (inChar == 'Ò') || (inChar == 'Ó') || (inChar == 'Ô') || (inChar == 'Õ') || (inChar == 'Ö') || (inChar == '?') || (inChar == 'Ø') || (inChar == 'Ù') || (inChar == 'Ú') || (inChar == 'Û') || (inChar == 'Ü') || (inChar == 'Ý') || (inChar == '?')) {
    return true;
  }
  return false;
}
