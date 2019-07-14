/** 
 * Encodes some exceptions where "C" is silent
 * @return true if encoding handled in this routine, false if not
 */
boolean Encode_Silent_C(){
  if (StringAt((m_current + 1),1,"T","S","")) {
    if (StringAt(0,11,"CONNECTICUT","") || StringAt(0,6,"INDICT","TUCSON","")) {
      m_current++;
      return true;
    }
  }
  return false;
}
