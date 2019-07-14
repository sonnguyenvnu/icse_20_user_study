private boolean hasThread(ASTExtendsList extendsList){
  List<ASTClassOrInterfaceType> typeList=extendsList.findChildrenOfType(ASTClassOrInterfaceType.class);
  if (typeList == null || typeList.isEmpty()) {
    return false;
  }
  for (  ASTClassOrInterfaceType type : typeList) {
    if (type.getType() == Thread.class) {
      return true;
    }
  }
  return false;
}
