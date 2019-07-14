@Override public Description matchBinary(BinaryTree tree,VisitorState state){
  Boolean constValue=booleanValue(tree);
  if (constValue == null) {
    return Description.NO_MATCH;
  }
  return buildDescription(tree).addFix(SuggestedFix.replace(tree,constValue.toString())).setMessage(String.format("This expression always evalutes to `%s`, prefer a boolean literal for clarity.",constValue)).build();
}
