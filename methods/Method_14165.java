/** 
 * Encode non-final 'S' in words from the french where they are not pronounced.
 * @return true if encoding handled in this routine, false if not
 */
boolean Encode_Silent_French_S_Internal(){
  if (StringAt((m_current - 2),9,"DESCARTES","") || StringAt((m_current - 2),7,"DESCHAM","DESPRES","DESROCH","DESROSI","DESJARD","DESMARA","DESCHEN","DESHOTE","DESLAUR","") || StringAt((m_current - 2),6,"MESNES","") || StringAt((m_current - 5),8,"DUQUESNE","DUCHESNE","") || StringAt((m_current - 7),10,"BEAUCHESNE","") || StringAt((m_current - 3),7,"FRESNEL","") || StringAt((m_current - 3),9,"GROSVENOR","") || StringAt((m_current - 4),10,"LOUISVILLE","") || StringAt((m_current - 7),10,"ILLINOISAN","")) {
    m_current++;
    return true;
  }
  return false;
}
