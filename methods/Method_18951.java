@Override public Set<String> getSupportedAnnotationTypes(){
  return new LinkedHashSet<>(Arrays.asList(ClassNames.LAYOUT_SPEC.toString(),ClassNames.MOUNT_SPEC.toString()));
}
