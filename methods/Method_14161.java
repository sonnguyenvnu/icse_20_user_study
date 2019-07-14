/** 
 * Encode "-COUP-" where "-P-" is not pronounced since the word is from the french
 * @return true if encoding handled in this routine, false if not
 */
boolean Encode_COUP(){
  if ((m_current == m_last) && StringAt((m_current - 3),4,"COUP","") && !StringAt((m_current - 5),6,"RECOUP","")) {
    m_current++;
    return true;
  }
  return false;
}
