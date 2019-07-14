/** 
 * Detect a number of contexts of '-ng-' that will take a 'hard' 'g' despite being followed by a front vowel.
 * @return true if 'hard' 'g' detected, false if not
 */
boolean Internal_Hard_NG(){
  if ((StringAt((m_current - 3),4,"DANG","FANG","SING","") && !StringAt((m_current - 5),8,"DISINGEN","")) || StringAt(0,5,"INGEB","ENGEB","") || (StringAt((m_current - 3),4,"RING","WING","HANG","LONG","") && !(StringAt((m_current - 4),5,"CRING","FRING","ORANG","TWING","CHANG","PHANG","") || StringAt((m_current - 5),6,"SYRING","") || StringAt((m_current - 3),7,"RINGENC","RINGENT","LONGITU","LONGEVI","") || (StringAt(m_current,4,"GELO","GINO","") && ((m_current + 3) == m_last)))) || (StringAt((m_current - 1),3,"NGY","") && !(StringAt((m_current - 3),5,"RANGY","MANGY","MINGY","") || StringAt((m_current - 4),6,"SPONGY","STINGY","")))) {
    return true;
  }
  return false;
}
