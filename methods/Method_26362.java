private Description.Builder describeClass(Tree tree,ClassSymbol sym,AnnotationInfo annotation,Violation info){
  String message;
  if (sym.getQualifiedName().contentEquals(annotation.typeName())) {
    message="type annotated with @Immutable could not be proven immutable: " + info.message();
  }
 else {
    message=String.format("Class extends @Immutable type %s, but is not immutable: %s",annotation.typeName(),info.message());
  }
  return buildDescription(tree).setMessage(message);
}
