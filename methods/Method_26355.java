Description.Builder describeClass(Tree tree,Violation info){
  String message="annotations should be immutable: " + info.message();
  return buildDescription(tree).setMessage(message);
}
