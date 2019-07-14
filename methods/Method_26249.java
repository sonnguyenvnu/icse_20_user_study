private static Collector<JCVariableDecl,?,ImmutableMultimap<Integer,JCVariableDecl>> collectByEditDistanceTo(String baseName){
  return Collectors.collectingAndThen(Multimaps.toMultimap((  JCVariableDecl varDecl) -> LevenshteinEditDistance.getEditDistance(baseName,varDecl.name.toString()),varDecl -> varDecl,LinkedHashMultimap::create),ImmutableMultimap::copyOf);
}
