@Override protected Collection<String> defaultSuppressionAnnotations(){
  Collection<String> defaultValues=new ArrayList<>();
  defaultValues.addAll(super.defaultSuppressionAnnotations());
  defaultValues.add("lombok.experimental.Delegate");
  return defaultValues;
}
