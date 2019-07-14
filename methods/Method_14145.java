/** 
 * Encode "-AULT-", found in a french names
 * @return true if encoding handled in this routine, false if not
 */
boolean Encode_French_AULT(){
  if ((m_current > 3) && (StringAt((m_current - 3),5,"RAULT","NAULT","BAULT","SAULT","GAULT","CAULT","") || StringAt((m_current - 4),6,"REAULT","RIAULT","NEAULT","BEAULT","")) && !(RootOrInflections(m_inWord,"ASSAULT") || StringAt((m_current - 8),10,"SOMERSAULT","") || StringAt((m_current - 9),11,"SUMMERSAULT",""))) {
    m_current+=2;
    return true;
  }
  return false;
}
