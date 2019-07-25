public boolean satisfies(SNode type){
  SubtypingManager subtypingManager=TypeChecker.getInstance().getSubtypingManager();
  for (  SNode w : myEquals) {
    if (!HUtil.isRuntimeHoleType(w) && !MatchingUtil.matchNodes(w,type)) {
      return false;
    }
  }
  for (  SNode supertype : mySuperTypes) {
    if (!subtypingManager.isSubtype(type,supertype,true))     return false;
  }
  for (  SNode supertype : myStrongSuperTypes) {
    if (!subtypingManager.isSubtype(type,supertype,false))     return false;
  }
  for (  SNode subtype : mySubTypes) {
    if (!subtypingManager.isSubtype(subtype,type,true))     return false;
  }
  for (  SNode subtype : myStrongSubTypes) {
    if (!subtypingManager.isSubtype(subtype,type,false))     return false;
  }
  for (  SNode comparable : myComparableTypes) {
    if (!subtypingManager.isComparable(comparable,type,true))     return false;
  }
  for (  SNode comparable : myStrongComparableTypes) {
    if (!subtypingManager.isComparable(comparable,type,false))     return false;
  }
  return true;
}
