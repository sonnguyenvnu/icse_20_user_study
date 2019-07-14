/** 
 * Encode special cases "Mr." and "Mrs."
 * @return true if encoding handled in this routine, false if not
 */
boolean Encode_MR_And_MRS(){
  if ((m_current == 0) && StringAt(m_current,2,"MR","")) {
    if ((m_length == 2) && StringAt(m_current,2,"MR","")) {
      if (m_encodeVowels) {
        MetaphAdd("MASTAR");
      }
 else {
        MetaphAdd("MSTR");
      }
      m_current+=2;
      return true;
    }
 else     if ((m_length == 3) && StringAt(m_current,3,"MRS","")) {
      if (m_encodeVowels) {
        MetaphAdd("MASAS");
      }
 else {
        MetaphAdd("MSS");
      }
      m_current+=3;
      return true;
    }
  }
  return false;
}
