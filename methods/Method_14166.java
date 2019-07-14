/** 
 * Encode silent 'S' in context of "-ISL-"
 * @return true if encoding handled in this routine, false if not
 */
boolean Encode_ISL(){
  if ((StringAt((m_current - 2),4,"LISL","LYSL","AISL","") && !StringAt((m_current - 3),7,"PAISLEY","BAISLEY","ALISLAM","ALISLAH","ALISLAA","")) || ((m_current == 1) && ((StringAt((m_current - 1),4,"ISLE","") || StringAt((m_current - 1),5,"ISLAN","")) && !StringAt((m_current - 1),5,"ISLEY","ISLER","")))) {
    m_current++;
    return true;
  }
  return false;
}
