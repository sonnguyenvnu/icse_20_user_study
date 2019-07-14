/** 
 * Encode cases where 'G' is silent at beginning of word
 * @return true if encoding handled in this routine, false if not
 */
boolean Encode_Silent_G_At_Beginning(){
  if ((m_current == 0) && StringAt(m_current,2,"GN","")) {
    m_current+=1;
    return true;
  }
  return false;
}
