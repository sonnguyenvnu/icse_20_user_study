/** 
 * Encode some contexts where "g" is silent
 * @return true if encoding handled in this routine, false if not
 */
boolean Encode_Silent_G(){
  if ((((m_current + 1) == m_last) && (StringAt((m_current - 1),3,"EGM","IGM","AGM","") || StringAt(m_current,2,"GT",""))) || (StringAt(0,5,"HUGES","") && (m_length == 5))) {
    m_current++;
    return true;
  }
  if (StringAt(0,2,"NG","") && (m_current != m_last)) {
    m_current++;
    return true;
  }
  return false;
}
