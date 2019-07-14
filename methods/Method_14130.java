/** 
 * Encodes cases where "-CH-" is not pronounced
 * @return true if encoding handled in this routine, false if not
 */
boolean Encode_Silent_CH(){
  if (StringAt((m_current - 2),7,"FUCHSIA","") || StringAt((m_current - 2),5,"YACHT","") || StringAt(0,8,"STRACHAN","") || StringAt(0,8,"CRICHTON","") || (StringAt((m_current - 3),6,"DRACHM","")) && !StringAt((m_current - 3),7,"DRACHMA","")) {
    m_current+=2;
    return true;
  }
  return false;
}
