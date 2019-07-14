private Description.Builder describeAnonymous(Tree tree,Type superType,Violation info){
  String message=String.format("Class extends @Immutable type %s, but is not immutable: %s",superType,info.message());
  return buildDescription(tree).setMessage(message);
}
