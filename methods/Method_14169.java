/** 
 * Encode cases where americans recognize "-EZ" as part of a french word where Z not pronounced
 * @return true if encoding handled in this routine, false if not
 */
boolean Encode_French_EZ(){
  if (((m_current == 3) && StringAt((m_current - 3),4,"CHEZ","")) || StringAt((m_current - 5),6,"RENDEZ","")) {
    m_current++;
    return true;
  }
  return false;
}
