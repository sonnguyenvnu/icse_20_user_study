/** 
 * Encode "-ILLA-" and "-ILLE-" in spanish and french contexts were americans know to pronounce it as a 'Y'
 * @return true if encoding handled in this routine, false if not
 */
boolean Encode_LL_As_Vowel_Special_Cases(){
  if (StringAt((m_current - 5),8,"TORTILLA","") || StringAt((m_current - 8),11,"RATATOUILLE","") || (StringAt(0,5,"GUILL","VEILL","GAILL","") && !(StringAt((m_current - 3),7,"GUILLOT","GUILLOR","GUILLEN","") || (StringAt(0,5,"GUILL","") && (m_length == 5)))) || StringAt(0,7,"BROUILL","GREMILL","ROBILL","") || (StringAt((m_current - 2),5,"EILLE","") && ((m_current + 2) == m_last) && !StringAt((m_current - 5),8,"REVEILLE",""))) {
    m_current+=2;
    return true;
  }
  return false;
}
