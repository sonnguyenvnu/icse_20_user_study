/** 
 * Encode "-OULX", always found in a french word
 * @return true if encoding handled in this routine, false if not
 */
boolean Encode_French_OULX(){
  if (StringAt((m_current - 2),4,"OULX","") && ((m_current + 1) == m_last)) {
    m_current+=2;
    return true;
  }
  return false;
}
