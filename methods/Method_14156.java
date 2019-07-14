/** 
 * Test whether "-B-" is silent in these contexts
 * @return true if 'B' is silent in this context
 */
boolean Test_Silent_MB_2(){
  if ((CharAt(m_current + 1) == 'B') && (m_current > 1) && (((m_current + 1) == m_last) || StringAt((m_current + 2),3,"ING","ABL","") || StringAt((m_current + 2),4,"LIKE","") || ((CharAt(m_current + 2) == 'S') && ((m_current + 2) == m_last)) || StringAt((m_current - 5),7,"BUNCOMB","") || (StringAt((m_current + 2),2,"ED","ER","") && ((m_current + 3) == m_last) && (StringAt(0,5,"CLIMB","PLUMB","") || !StringAt((m_current - 1),5,"IMBER","AMBER","EMBER","UMBER","")) && !StringAt((m_current - 2),6,"CUMBER","SOMBER","")))) {
    return true;
  }
  return false;
}
