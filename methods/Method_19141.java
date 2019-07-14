@Override public Set<String> getSupportedAnnotationTypes(){
  return new LinkedHashSet<>(Arrays.asList(SectionClassNames.GROUP_SECTION_SPEC.toString(),SectionClassNames.DIFF_SECTION_SPEC.toString()));
}
