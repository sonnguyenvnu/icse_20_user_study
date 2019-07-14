/** 
 * Detect words where "-gy-", "-gie-", "-gee-",  or "-gio-" get a 'hard' 'g' even though this is  usually a 'soft' 'g' context
 * @return true if 'hard' 'g' detected
 */
boolean Internal_Hard_G_Open_Syllable(){
  if (StringAt((m_current + 1),3,"EYE","") || StringAt((m_current - 2),4,"FOGY","POGY","YOGI","") || StringAt((m_current - 2),5,"MAGEE","MCGEE","HAGIO","") || StringAt((m_current - 1),4,"RGEY","OGEY","") || StringAt((m_current - 3),5,"HOAGY","STOGY","PORGY","") || StringAt((m_current - 5),8,"CARNEGIE","") || (StringAt((m_current - 1),4,"OGEY","OGIE","") && ((m_current + 2) == m_last))) {
    return true;
  }
  return false;
}
