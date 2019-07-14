private boolean occurenceIsArrayAccess(NameOccurrence occ,String arrayName){
  if (occ.getLocation() instanceof ASTName) {
    ASTPrimarySuffix suffix=occ.getLocation().getFirstParentOfType(ASTPrimarySuffix.class);
    if (suffix == null || !suffix.isArrayDereference()) {
      return false;
    }
    return suffix.hasDescendantMatchingXPath("./Expression/PrimaryExpression[count(*)" + "=1]/PrimaryPrefix/Name[@Image='" + occ.getImage() + "']") && suffix.hasDescendantMatchingXPath("../PrimaryPrefix/Name[@Image='" + arrayName + "']") && !suffix.hasDescendantMatchingXPath("../../AssignmentOperator");
  }
  return false;
}
