/** 
 * Detect words where "-ge-" or "-gi-" get a 'hard' 'g' even though this is usually a 'soft' 'g' context
 * @return true if 'hard' 'g' detected
 */
boolean Internal_Hard_G_Other(){
  if ((StringAt(m_current,4,"GETH","GEAR","GEIS","GIRL","GIVI","GIVE","GIFT","GIRD","GIRT","GILV","GILD","GELD","") && !StringAt((m_current - 3),6,"GINGIV","")) || (StringAt((m_current + 1),3,"ISH","") && (m_current > 0) && !StringAt(0,4,"LARG","")) || (StringAt((m_current - 2),5,"MAGED","MEGID","") && !((m_current + 2) == m_last)) || StringAt(m_current,3,"GEZ","") || StringAt(0,4,"WEGE","HAGE","") || (StringAt((m_current - 2),6,"ONGEST","UNGEST","") && ((m_current + 3) == m_last) && !StringAt((m_current - 3),7,"CONGEST","")) || StringAt(0,5,"VOEGE","BERGE","HELGE","") || (StringAt(0,4,"ENGE","BOGY","") && (m_length == 4)) || StringAt(m_current,6,"GIBBON","") || StringAt(0,10,"CORREGIDOR","") || StringAt(0,8,"INGEBORG","") || (StringAt(m_current,4,"GILL","") && (((m_current + 3) == m_last) || ((m_current + 4) == m_last)) && !StringAt(0,8,"STURGILL",""))) {
    return true;
  }
  return false;
}
