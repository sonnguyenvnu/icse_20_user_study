private String prefix(){
  if (parentName == null) {
    return "";
  }
  return (((parentName instanceof IHName) ? ((IHName)parentName).fqName() : parentName.toString())) + ".";
}
