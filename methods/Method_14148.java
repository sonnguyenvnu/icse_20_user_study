/** 
 * Encodes contexts where '-L-' is silent in 'LK', 'LV'
 * @return true if encoding handled in this routine, false if not
 */
boolean Encode_Silent_L_In_LK_LV(){
  if ((StringAt((m_current - 2),4,"WALK","YOLK","FOLK","HALF","TALK","CALF","BALK","CALK","") || (StringAt((m_current - 2),4,"POLK","") && !StringAt((m_current - 2),5,"POLKA","WALKO","")) || (StringAt((m_current - 2),4,"HALV","") && !StringAt((m_current - 2),5,"HALVA","HALVO","")) || (StringAt((m_current - 3),5,"CAULK","CHALK","BAULK","FAULK","") && !StringAt((m_current - 4),6,"SCHALK","")) || (StringAt((m_current - 2),5,"SALVE","CALVE","") || StringAt((m_current - 2),6,"SOLDER","")) && !StringAt((m_current - 2),6,"SALVER","CALVER","")) && !StringAt((m_current - 5),9,"GONSALVES","GONCALVES","") && !StringAt((m_current - 2),6,"BALKAN","TALKAL","") && !StringAt((m_current - 3),5,"PAULK","CHALF","")) {
    m_current++;
    return true;
  }
  return false;
}
