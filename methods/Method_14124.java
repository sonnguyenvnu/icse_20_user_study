/** 
 * Tests and encodes cases where non-initial 'e' is never pronounced Only executed if non initial vowel encoding is turned on
 * @return true if encoded as silent - no addition to m_metaph key
 */
boolean E_Silent(){
  if (E_Pronounced_At_End()) {
    return false;
  }
  if ((m_current == m_last) || ((StringAt(m_last,1,"S","D","") && (m_current > 1) && ((m_current + 1) == m_last) && !(StringAt((m_current - 1),3,"TED","SES","CES","") || StringAt(0,9,"ANTIPODES","ANOPHELES","") || StringAt(0,8,"MOHAMMED","MUHAMMED","MOUHAMED","") || StringAt(0,7,"MOHAMED","") || StringAt(0,6,"NORRED","MEDVED","MERCED","ALLRED","KHALED","RASHED","MASJED","") || StringAt(0,5,"JARED","AHMED","HAMED","JAVED","") || StringAt(0,4,"ABED","IMED","")))) || (StringAt((m_current + 1),4,"NESS","LESS","") && ((m_current + 4) == m_last)) || (StringAt((m_current + 1),2,"LY","") && ((m_current + 2) == m_last) && !StringAt(0,6,"CICELY",""))) {
    return true;
  }
  return false;
}
