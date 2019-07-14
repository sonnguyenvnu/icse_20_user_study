/** 
 * Encode cases where "-P-" is silent at the start of a word
 * @return true if encoding handled in this routine, false if not
 */
boolean Encode_Silent_P_At_Beginning(){
  if ((m_current == 0) && StringAt(m_current,2,"PN","PF","PS","PT","")) {
    m_current+=1;
    return true;
  }
  return false;
}
