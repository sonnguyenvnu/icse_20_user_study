protected String findReverseWordsMatchInParentNodes(VisitorState state){
  for (  Tree tree : state.getPath()) {
    Optional<String> name=getName(tree);
    if (name.isPresent()) {
      for (      String term : NamingConventions.splitToLowercaseTerms(name.get())) {
        if (reverseWordsTerms.contains(term)) {
          return term;
        }
      }
    }
  }
  return null;
}
