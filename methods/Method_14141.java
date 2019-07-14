/** 
 * Detect a number of contexts, mostly german names, that take a 'hard' 'g'.
 * @return true if 'hard' 'g' detected, false if not
 */
boolean Internal_Hard_GEN_GIN_GET_GIT(){
  if ((StringAt((m_current - 3),6,"FORGET","TARGET","MARGIT","MARGET","TURGEN","BERGEN","MORGEN","JORGEN","HAUGEN","JERGEN","JURGEN","LINGEN","BORGEN","LANGEN","KLAGEN","STIGER","BERGER","") && !StringAt(m_current,7,"GENETIC","GENESIS","") && !StringAt((m_current - 4),8,"PLANGENT","")) || (StringAt((m_current - 3),6,"BERGIN","FEAGIN","DURGIN","") && ((m_current + 2) == m_last)) || (StringAt((m_current - 2),5,"ENGEN","") && !StringAt((m_current + 3),3,"DER","ETI","ESI","")) || StringAt((m_current - 4),7,"JUERGEN","") || StringAt(0,5,"NAGIN","MAGIN","HAGIN","") || (StringAt(0,5,"ENGIN","DEGEN","LAGEN","MAGEN","NAGIN","") && (m_length == 5)) || (StringAt((m_current - 2),5,"BEGET","BEGIN","HAGEN","FAGIN","BOGEN","WIGIN","NTGEN","EIGEN","WEGEN","WAGEN","") && !StringAt((m_current - 5),8,"OSPHAGEN",""))) {
    return true;
  }
  return false;
}
