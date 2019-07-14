static boolean isSystemLevelClass(ASTUserClass node){
  List<TypeRef> interfaces=node.getNode().getDefiningType().getCodeUnitDetails().getInterfaceTypeRefs();
  for (  TypeRef intObject : interfaces) {
    if (isWhitelisted(intObject.getNames())) {
      return true;
    }
  }
  return false;
}
