/** 
 * Call routines to encode "-LL-", in proper order
 * @return true if encoding handled in this routine, false if not
 */
boolean Encode_LL_As_Vowel_Cases(){
  if (CharAt(m_current + 1) == 'L') {
    if (Encode_LL_As_Vowel_Special_Cases()) {
      return true;
    }
 else     if (Encode_LL_As_Vowel()) {
      return true;
    }
    m_current+=2;
  }
 else {
    m_current++;
  }
  return false;
}
