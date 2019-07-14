/** 
 * Tests for words where an 'E' at the end of the word is pronounced special cases, mostly from the greek, spanish, japanese,  italian, and french words normally having an acute accent.  also, pronouns and articles Many Thanks to ali, QuentinCompson, JeffCO, ToonScribe, Xan, Trafalz, and VictorLaszlo, all of them atriots from the Eschaton,  for all their fine contributions!
 * @return true if 'E' at end is pronounced
 */
boolean E_Pronounced_At_End(){
  if ((m_current == m_last) && (StringAt((m_current - 6),7,"STROPHE","") || (m_length == 2) || ((m_length == 3) && !IsVowel(0)) || (StringAt((m_last - 2),3,"BKE","DKE","FKE","KKE","LKE","NKE","MKE","PKE","TKE","VKE","ZKE","") && !StringAt(0,5,"FINKE","FUNKE","") && !StringAt(0,6,"FRANKE","")) || StringAt((m_last - 4),5,"SCHKE","") || (StringAt(0,4,"ACME","NIKE","CAFE","RENE","LUPE","JOSE","ESME","") && (m_length == 4)) || (StringAt(0,5,"LETHE","CADRE","TILDE","SIGNE","POSSE","LATTE","ANIME","DOLCE","CROCE","ADOBE","OUTRE","JESSE","JAIME","JAFFE","BENGE","RUNGE","CHILE","DESME","CONDE","URIBE","LIBRE","ANDRE","") && (m_length == 5)) || (StringAt(0,6,"HECATE","PSYCHE","DAPHNE","PENSKE","CLICHE","RECIPE","TAMALE","SESAME","SIMILE","FINALE","KARATE","RENATE","SHANTE","OBERLE","COYOTE","KRESGE","STONGE","STANGE","SWAYZE","FUENTE","SALOME","URRIBE","") && (m_length == 6)) || (StringAt(0,7,"ECHIDNE","ARIADNE","MEINEKE","PORSCHE","ANEMONE","EPITOME","SYNCOPE","SOUFFLE","ATTACHE","MACHETE","KARAOKE","BUKKAKE","VICENTE","ELLERBE","VERSACE","") && (m_length == 7)) || (StringAt(0,8,"PENELOPE","CALLIOPE","CHIPOTLE","ANTIGONE","KAMIKAZE","EURIDICE","YOSEMITE","FERRANTE","") && (m_length == 8)) || (StringAt(0,9,"HYPERBOLE","GUACAMOLE","XANTHIPPE","") && (m_length == 9)) || (StringAt(0,10,"SYNECDOCHE","") && (m_length == 10)))) {
    return true;
  }
  return false;
}
