/** 
 * Encode "-CORPS-" where "-PS-" not pronounced since the cognate is here from the french
 * @return true if encoding handled in this routine, false if not
 */
boolean Encode_RPS(){
  if (StringAt((m_current - 3),5,"CORPS","") && !StringAt((m_current - 3),6,"CORPSE","")) {
    m_current+=2;
    return true;
  }
  return false;
}
