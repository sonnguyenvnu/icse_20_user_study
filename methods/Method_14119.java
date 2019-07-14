/** 
 * Skips over vowels in a string. Has exceptions for skipping consonants that will not be encoded.
 * @param at position, in string to be encoded, of character to start skipping from
 * @return position of next consonant in string to be encoded 
 */
int SkipVowels(int at){
  if (at < 0) {
    return 0;
  }
  if (at >= m_length) {
    return m_length;
  }
  char it=CharAt(at);
  while (IsVowel(it) || (it == 'W')) {
    if (StringAt(at,4,"WICZ","WITZ","WIAK","") || StringAt((at - 1),5,"EWSKI","EWSKY","OWSKI","OWSKY","") || (StringAt(at,5,"WICKI","WACKI","") && ((at + 4) == m_last))) {
      break;
    }
    at++;
    if (((CharAt(at - 1) == 'W') && (CharAt(at) == 'H')) && !(StringAt(at,3,"HOP","") || StringAt(at,4,"HIDE","HARD","HEAD","HAWK","HERD","HOOK","HAND","HOLE","") || StringAt(at,5,"HEART","HOUSE","HOUND","") || StringAt(at,6,"HAMMER",""))) {
      at++;
    }
    if (at > (m_length - 1)) {
      break;
    }
    it=CharAt(at);
  }
  return at;
}
