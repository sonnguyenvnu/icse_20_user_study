private static boolean areSingleArgumentsSelfDocumenting(Tree tree){
  if (tree instanceof NewClassTree) {
    return Ascii.toLowerCase(((NewClassTree)tree).getIdentifier().toString()).contains("boolean");
  }
  return true;
}
