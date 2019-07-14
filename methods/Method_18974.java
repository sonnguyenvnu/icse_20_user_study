static boolean isKotlinSingleton(TypeElement element){
  return element.getKind() == ElementKind.CLASS && element.getEnclosedElements().stream().anyMatch(e -> {
    final CharSequence instanceFieldName="INSTANCE";
    return e.getSimpleName().contentEquals(instanceFieldName) && e.asType().toString().equals(element.getQualifiedName().toString()) && e.getModifiers().containsAll(ImmutableList.of(Modifier.PUBLIC,Modifier.STATIC,Modifier.FINAL));
  }
);
}
