@Override public String toString(){
  String res="ClassScope (" + className + "): ";
  Map<ClassNameDeclaration,List<NameOccurrence>> classNames=getClassDeclarations();
  Map<MethodNameDeclaration,List<NameOccurrence>> methodNames=getMethodDeclarations();
  Map<VariableNameDeclaration,List<NameOccurrence>> variableNames=getVariableDeclarations();
  if (!classNames.isEmpty()) {
    res+="(" + classNames.keySet() + ")";
  }
  if (!methodNames.isEmpty()) {
    for (    MethodNameDeclaration mnd : methodNames.keySet()) {
      res+=mnd.toString();
      int usages=methodNames.get(mnd).size();
      res+="(begins at line " + mnd.getNode().getBeginLine() + ", " + usages + " usages)";
      res+=",";
    }
  }
  if (!variableNames.isEmpty()) {
    res+="(" + variableNames.keySet() + ")";
  }
  return res;
}
