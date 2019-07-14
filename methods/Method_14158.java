/** 
 * Tests for contexts where "-N-" is silent when after "-M-"
 * @return true if "-N-" is silent in these contexts
 */
boolean Test_MN(){
  if ((CharAt(m_current + 1) == 'N') && (((m_current + 1) == m_last) || (StringAt((m_current + 2),3,"ING","EST","") && ((m_current + 4) == m_last)) || ((CharAt(m_current + 2) == 'S') && ((m_current + 2) == m_last)) || (StringAt((m_current + 2),2,"LY","ER","ED","") && ((m_current + 3) == m_last)) || StringAt((m_current - 2),9,"DAMNEDEST","") || StringAt((m_current - 5),9,"GODDAMNIT",""))) {
    return true;
  }
  return false;
}
