/** 
 * @return true if encoding handled in this routine, false if not
 */
boolean Encode_Silent_GH(){
  if (((((m_current > 1) && StringAt((m_current - 2),1,"B","H","D","G","L","")) || ((m_current > 2) && StringAt((m_current - 3),1,"B","H","D","K","W","N","P","V","") && !StringAt(0,6,"ENOUGH","")) || ((m_current > 3) && StringAt((m_current - 4),1,"B","H","")) || ((m_current > 3) && StringAt((m_current - 4),2,"PL","SL","")) || ((m_current > 0) && ((CharAt(m_current - 1) == 'I') || StringAt(0,4,"PUGH","") || (StringAt((m_current - 1),3,"AGH","") && ((m_current + 1) == m_last)) || StringAt((m_current - 4),6,"GERAGH","DRAUGH","") || (StringAt((m_current - 3),5,"GAUGH","GEOGH","MAUGH","") && !StringAt(0,9,"MCGAUGHEY","")) || (StringAt((m_current - 2),4,"OUGH","") && (m_current > 3) && !StringAt((m_current - 4),6,"CCOUGH","ENOUGH","TROUGH","CLOUGH",""))))) && (StringAt((m_current - 3),5,"VAUGH","FEIGH","LEIGH","") || StringAt((m_current - 2),4,"HIGH","TIGH","") || ((m_current + 1) == m_last) || (StringAt((m_current + 2),2,"IE","EY","ES","ER","ED","TY","") && ((m_current + 3) == m_last) && !StringAt((m_current - 5),9,"GALLAGHER","")) || (StringAt((m_current + 2),1,"Y","") && ((m_current + 2) == m_last)) || (StringAt((m_current + 2),3,"ING","OUT","") && ((m_current + 4) == m_last)) || (StringAt((m_current + 2),4,"ERTY","") && ((m_current + 5) == m_last)) || (!IsVowel(m_current + 2) || StringAt((m_current - 3),5,"GAUGH","GEOGH","MAUGH","") || StringAt((m_current - 4),8,"BROUGHAM","")))) && !(StringAt(0,6,"BALOGH","SABAGH","") || StringAt((m_current - 2),7,"BAGHDAD","") || StringAt((m_current - 3),5,"WHIGH","") || StringAt((m_current - 5),7,"SABBAGH","AKHLAGH",""))) {
    m_current+=2;
    return true;
  }
  return false;
}
