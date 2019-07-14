/** 
 * Detect internal silent 'E's e.g. "roseman", "firestone"
 */
boolean Silent_Internal_E(){
  if ((StringAt(0,3,"OLE","") && E_Silent_Suffix(3) && !E_Pronouncing_Suffix(3)) || (StringAt(0,4,"BARE","FIRE","FORE","GATE","HAGE","HAVE","HAZE","HOLE","CAPE","HUSE","LACE","LINE","LIVE","LOVE","MORE","MOSE","MORE","NICE","RAKE","ROBE","ROSE","SISE","SIZE","WARE","WAKE","WISE","WINE","") && E_Silent_Suffix(4) && !E_Pronouncing_Suffix(4)) || (StringAt(0,5,"BLAKE","BRAKE","BRINE","CARLE","CLEVE","DUNNE","HEDGE","HOUSE","JEFFE","LUNCE","STOKE","STONE","THORE","WEDGE","WHITE","") && E_Silent_Suffix(5) && !E_Pronouncing_Suffix(5)) || (StringAt(0,6,"BRIDGE","CHEESE","") && E_Silent_Suffix(6) && !E_Pronouncing_Suffix(6)) || StringAt((m_current - 5),7,"CHARLES","")) {
    return true;
  }
  return false;
}
