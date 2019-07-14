/** 
 * Encode cases where 'M' is silent at beginning of word
 * @return true if encoding handled in this routine, false if not
 */
boolean Encode_Silent_M_At_Beginning(){
  if ((m_current == 0) && StringAt(m_current,2,"MN","")) {
    m_current+=1;
    return true;
  }
  return false;
}
