/** 
 * Encode silent "-D-"
 * @return true if encoding handled in this routine, false if not
 */
boolean Encode_Silent_D(){
  if (StringAt((m_current - 2),9,"WEDNESDAY","") || StringAt((m_current - 3),7,"HANDKER","HANDSOM","WINDSOR","") || StringAt((m_current - 5),6,"PERNOD","ARTAUD","RENAUD","") || StringAt((m_current - 6),7,"RIMBAUD","MICHAUD","BICHAUD","")) {
    m_current++;
    return true;
  }
  return false;
}
