/** 
 * Encode cases where 'K' is not pronounced
 * @return true if encoding handled in this routine, false if not
 */
boolean Encode_Silent_K(){
  if ((m_current == 0) && StringAt(m_current,2,"KN","")) {
    if (!(StringAt((m_current + 2),5,"ESSET","IEVEL","") || StringAt((m_current + 2),3,"ISH",""))) {
      m_current+=1;
      return true;
    }
  }
  if ((StringAt((m_current + 1),3,"NOW","NIT","NOT","NOB","") && !StringAt(0,8,"BANKNOTE","")) || StringAt((m_current + 1),4,"NOCK","NUCK","NIFE","NACK","") || StringAt((m_current + 1),5,"NIGHT","")) {
    if ((m_current > 0) && CharAt(m_current - 1) == 'N') {
      m_current+=2;
    }
 else {
      m_current++;
    }
    return true;
  }
  return false;
}
