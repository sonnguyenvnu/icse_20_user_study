/** 
 * Encode "-EUIL-", always found in a french word
 * @return true if encoding handled in this routine, false if not
 */
boolean Encode_French_EUIL(){
  if (StringAt((m_current - 3),4,"EUIL","") && (m_current == m_last)) {
    m_current++;
    return true;
  }
  return false;
}
