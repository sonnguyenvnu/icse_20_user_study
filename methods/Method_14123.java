/** 
 * Tests for cases where non-initial 'o' is not pronounced Only executed if non initial vowel encoding is turned on
 * @return true if encoded as silent - no addition to m_metaph key
 */
boolean O_Silent(){
  if ((CharAt(m_current) == 'O') && StringAt((m_current - 2),4,"IRON","")) {
    if ((StringAt(0,4,"IRON","") || (StringAt((m_current - 2),4,"IRON","") && (m_last == (m_current + 1)))) && !StringAt((m_current - 2),6,"IRONIC","")) {
      return true;
    }
  }
  return false;
}
