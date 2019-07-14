/** 
 * Test if 'B' is silent in these contexts
 * @return true if 'B' is silent in this context
 */
boolean Test_Silent_MB_1(){
  if (((m_current == 3) && StringAt((m_current - 3),5,"THUMB","")) || ((m_current == 2) && StringAt((m_current - 2),4,"DUMB","BOMB","DAMN","LAMB","NUMB","TOMB",""))) {
    return true;
  }
  return false;
}
