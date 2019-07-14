boolean Hard_GE_At_End(){
  if (StringAt(0,6,"RENEGE","STONGE","STANGE","PRANGE","KRESGE","") || StringAt(0,5,"BYRGE","BIRGE","BERGE","HAUGE","") || StringAt(0,4,"HAGE","") || StringAt(0,5,"LANGE","SYNGE","BENGE","RUNGE","HELGE","") || StringAt(0,4,"INGE","LAGE","")) {
    return true;
  }
  return false;
}
