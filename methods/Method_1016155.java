public static boolean equals(List<NinePatchInterval> npi1,List<NinePatchInterval> npi2){
  if (npi1 == null && npi2 == null) {
    return true;
  }
 else   if (npi1 == null || npi2 == null) {
    return false;
  }
 else   if (npi1.size() != npi2.size()) {
    return false;
  }
 else {
    for (int i=0; i < npi1.size(); i++) {
      if (!npi1.get(i).equals(npi2.get(i))) {
        return false;
      }
    }
    return true;
  }
}
