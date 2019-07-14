/** 
 * Test if 'B' is pronounced in this context
 * @return true if 'B' is pronounced in this context
 */
boolean Test_Pronounced_MB(){
  if (StringAt((m_current - 2),6,"NUMBER","") || (StringAt((m_current + 2),1,"A","") && !StringAt((m_current - 2),7,"DUMBASS","")) || StringAt((m_current + 2),1,"O","") || StringAt((m_current - 2),6,"LAMBEN","LAMBER","LAMBET","TOMBIG","LAMBRE","")) {
    return true;
  }
  return false;
}
