@Override public Description matchCompilationUnit(CompilationUnitTree tree,VisitorState state){
  if (tree.getTypeDecls().size() <= 1) {
    return Description.NO_MATCH;
  }
  List<String> names=new ArrayList<>();
  for (  Tree member : tree.getTypeDecls()) {
    if (member instanceof ClassTree) {
      ClassTree classMember=(ClassTree)member;
switch (classMember.getKind()) {
case CLASS:
case INTERFACE:
case ANNOTATION_TYPE:
case ENUM:
        if (isSuppressed(classMember)) {
          return Description.NO_MATCH;
        }
      names.add(classMember.getSimpleName().toString());
    break;
default :
  break;
}
}
}
if (names.size() <= 1) {
return Description.NO_MATCH;
}
String message=String.format("Expected at most one top-level class declaration, instead found: %s",Joiner.on(", ").join(names));
return buildDescription(firstNonNull(tree.getPackageName(),tree.getTypeDecls().get(0))).setMessage(message).build();
}
