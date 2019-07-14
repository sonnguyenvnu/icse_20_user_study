public boolean suppresses(Rule rule){
  final String ruleAnno="PMD." + rule.getName();
  if (hasImageEqualTo("SuppressWarnings")) {
    for (    ASTAnnotationParameter param : findChildrenOfType(ASTAnnotationParameter.class)) {
      String image=param.getImage();
      if (image != null) {
        Set<String> paramValues=new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        paramValues.addAll(Arrays.asList(image.replaceAll("\\s+","").split(",")));
        if (paramValues.contains("PMD") || paramValues.contains(ruleAnno) || paramValues.contains("all")) {
          return true;
        }
      }
    }
  }
  return false;
}
