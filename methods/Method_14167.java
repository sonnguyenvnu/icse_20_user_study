/** 
 * Encode the many cases where americans are aware that a certain word is french and know to not pronounce the 'T'
 * @return true if encoding handled in this routine, false if notTOUCHET CHABOT BENOIT
 */
boolean Encode_Silent_French_T(){
  if (((m_current == m_last) && StringAt((m_current - 4),5,"MONET","GENET","CHAUT","")) || StringAt((m_current - 2),9,"POTPOURRI","") || StringAt((m_current - 3),9,"BOATSWAIN","") || StringAt((m_current - 3),8,"MORTGAGE","") || (StringAt((m_current - 4),5,"BERET","BIDET","FILET","DEBUT","DEPOT","PINOT","TAROT","") || StringAt((m_current - 5),6,"BALLET","BUFFET","CACHET","CHALET","ESPRIT","RAGOUT","GOULET","CHABOT","BENOIT","") || StringAt((m_current - 6),7,"GOURMET","BOUQUET","CROCHET","CROQUET","PARFAIT","PINCHOT","CABARET","PARQUET","RAPPORT","TOUCHET","COURBET","DIDEROT","") || StringAt((m_current - 7),8,"ENTREPOT","CABERNET","DUBONNET","MASSENET","MUSCADET","RICOCHET","ESCARGOT","") || StringAt((m_current - 8),9,"SOBRIQUET","CABRIOLET","CASSOULET","OUBRIQUET","CAMEMBERT","")) && !StringAt((m_current + 1),2,"AN","RY","IC","OM","IN","")) {
    m_current++;
    return true;
  }
  return false;
}
