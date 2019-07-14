/** 
 * Test whether 'R' is silent in this context
 * @return true if 'R' is silent in this context
 */
boolean Test_Silent_R(){
  if (((m_current == m_last) && StringAt((m_current - 2),3,"IER","") && (StringAt((m_current - 5),3,"MET","VIV","LUC","") || StringAt((m_current - 6),4,"CART","DOSS","FOUR","OLIV","BUST","DAUM","ATEL","SONN","CORM","MERC","PELT","POIR","BERN","FORT","GREN","SAUC","GAGN","GAUT","GRAN","FORC","MESS","LUSS","MEUN","POTH","HOLL","CHEN","") || StringAt((m_current - 7),5,"CROUP","TORCH","CLOUT","FOURN","GAUTH","TROTT","DEROS","CHART","") || StringAt((m_current - 8),6,"CHEVAL","LAVOIS","PELLET","SOMMEL","TREPAN","LETELL","COLOMB","") || StringAt((m_current - 9),7,"CHARCUT","") || StringAt((m_current - 10),8,"CHARPENT",""))) || StringAt((m_current - 2),7,"SURBURB","WORSTED","") || StringAt((m_current - 2),9,"WORCESTER","") || StringAt((m_current - 7),8,"MONSIEUR","") || StringAt((m_current - 6),8,"POITIERS","")) {
    return true;
  }
  return false;
}
