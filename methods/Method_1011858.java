public static SNode join(SNode t1,SNode t2){
  if (TypecheckingFacade.getFromContext().isSubtype(t1,t2)) {
    return t2;
  }
 else   if (TypecheckingFacade.getFromContext().isSubtype(t2,t1)) {
    return t1;
  }
 else {
    boolean bf=bigType(t1) || bigType(t2);
    boolean cf=complexType(t1) || complexType(t2);
    boolean ff=floatType(t1) || floatType(t2);
    if (bf && cf) {
      return SNodeOperations.copyNode(qBigComplex);
    }
    if (ff && bf) {
      return SNodeOperations.copyNode(qBigDecimal);
    }
    return null;
  }
}
