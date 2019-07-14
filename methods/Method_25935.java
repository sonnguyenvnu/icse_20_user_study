@Override public Description matchClass(ClassTree tree,VisitorState state){
  ClassSymbol classSymbol=getSymbol(tree);
  MethodTree equalsDeclaration=null;
  MethodTree hashCodeDeclaration=null;
  for (  Tree member : tree.getMembers()) {
    if (!(member instanceof MethodTree)) {
      continue;
    }
    MethodTree methodTree=(MethodTree)member;
    if (hashCodeMethodDeclaration().matches(methodTree,state)) {
      hashCodeDeclaration=methodTree;
    }
 else     if (equalsMethodDeclaration().matches(methodTree,state)) {
      equalsDeclaration=methodTree;
    }
  }
  if (equalsDeclaration == null || hashCodeDeclaration == null) {
    return Description.NO_MATCH;
  }
  Map<MethodSymbol,ImmutableSet<Symbol>> fieldsByMethod=new HashMap<>();
  for (  Tree member : tree.getMembers()) {
    if (!(member instanceof MethodTree)) {
      continue;
    }
    MethodTree methodTree=(MethodTree)member;
    if (!methodTree.equals(equalsDeclaration) && !methodTree.equals(hashCodeDeclaration)) {
      FieldAccessFinder finder=FieldAccessFinder.scanMethod(state,classSymbol,methodTree);
      if (!finder.failed()) {
        fieldsByMethod.put(getSymbol(methodTree),finder.accessedFields());
      }
    }
  }
  FieldAccessFinder equalsScanner=FieldAccessFinder.scanMethod(state,classSymbol,equalsDeclaration,fieldsByMethod,HASH_CODE_METHODS);
  FieldAccessFinder hashCodeScanner=FieldAccessFinder.scanMethod(state,classSymbol,hashCodeDeclaration,fieldsByMethod,EQUALS_METHODS);
  if (equalsScanner.failed() || hashCodeScanner.failed()) {
    return Description.NO_MATCH;
  }
  ImmutableSet<Symbol> fieldsInHashCode=hashCodeScanner.accessedFields();
  ImmutableSet<Symbol> fieldsInEquals=equalsScanner.accessedFields();
  Set<Symbol> difference=new HashSet<>(Sets.difference(fieldsInHashCode,fieldsInEquals));
  difference.removeIf(f -> Ascii.toLowerCase(f.toString()).contains("hash"));
  String message=String.format(MESSAGE,difference);
  return difference.isEmpty() || fieldsInEquals.isEmpty() ? Description.NO_MATCH : buildDescription(hashCodeDeclaration).setMessage(message).build();
}
