private static Matcher<IdentifierTree> getMatcher(Tree tree,VisitorState state){
  return COLLECTION_TYPE.matches(tree,state) ? (t,s) -> collectionUsed(s) : (t,s) -> fluentBuilderUsed(s);
}
