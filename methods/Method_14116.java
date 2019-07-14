/** 
 * Test for close front vowels
 * @return true if close front vowel
 */
boolean Front_Vowel(int at){
  if (((CharAt(at) == 'E') || (CharAt(at) == 'I') || (CharAt(at) == 'Y'))) {
    return true;
  }
  return false;
}
