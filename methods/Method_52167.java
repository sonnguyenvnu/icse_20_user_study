@Override protected Collection<String> defaultSuppressionAnnotations(){
  return Collections.checkedList(Arrays.asList("java.lang.Override"),String.class);
}
