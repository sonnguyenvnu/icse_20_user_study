private static void deleteAll(List<AnnotationTree> scopes,Builder fixBuilder){
  scopes.forEach(fixBuilder::delete);
}
