private static boolean typeInAnnotations(TypeElement t,List<String> annotations){
  return annotations.stream().anyMatch(annotation -> t.getQualifiedName().contentEquals(annotation));
}
