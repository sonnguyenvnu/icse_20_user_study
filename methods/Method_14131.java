/** 
 * @return true if encoding handled in this routine, false if not
 */
boolean Encode_British_Silent_CE(){
  if ((StringAt((m_current + 1),5,"ESTER","") && ((m_current + 5) == m_last)) || StringAt((m_current + 1),10,"ESTERSHIRE","")) {
    return true;
  }
  return false;
}
