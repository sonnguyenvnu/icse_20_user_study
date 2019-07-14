/** 
 * Encodes "-UE".
 * @return true if encoding handled in this routine, false if not
 */
boolean Skip_Silent_UE(){
  if ((StringAt((m_current - 1),3,"QUE","GUE","") && !StringAt(0,8,"BARBEQUE","PALENQUE","APPLIQUE","") && !StringAt(0,6,"RISQUE","") && !StringAt((m_current - 3),5,"ARGUE","SEGUE","") && !StringAt(0,7,"PIROGUE","ENRIQUE","") && !StringAt(0,10,"COMMUNIQUE","")) && (m_current > 1) && (((m_current + 1) == m_last) || StringAt(0,7,"JACQUES",""))) {
    m_current=SkipVowels(m_current);
    return true;
  }
  return false;
}
