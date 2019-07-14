public static ImmutableList<PropJavadocModel> getPropJavadocs(Elements elements,TypeElement typeElement){
  final String unsanitizedJavadoc=elements.getDocComment(typeElement);
  if (unsanitizedJavadoc == null || unsanitizedJavadoc.isEmpty()) {
    return ImmutableList.of();
  }
  final String javadoc=JAVADOC_SANITIZER.matcher(unsanitizedJavadoc).replaceAll("");
  final String[] propJavadocs=javadoc.split("@prop ");
  final List<PropJavadocModel> propJavadocModels=new ArrayList<>(propJavadocs.length);
  for (int i=1, size=propJavadocs.length; i < size; i++) {
    final String propJavadoc=propJavadocs[i];
    final String[] propJavadocContents=propJavadoc.split(" ",2);
    if (propJavadocContents.length == 2) {
      propJavadocModels.add(new PropJavadocModel(propJavadocContents[0],propJavadocContents[1].replace('\n',' ')));
    }
  }
  return ImmutableList.copyOf(propJavadocModels);
}
