@Override protected Collection<String> defaultSuppressionAnnotations(){
  Collection<String> defaultValues=new ArrayList<>();
  defaultValues.addAll(super.defaultSuppressionAnnotations());
  defaultValues.add("java.lang.Deprecated");
  defaultValues.add("javafx.fxml.FXML");
  defaultValues.add("lombok.experimental.Delegate");
  return defaultValues;
}
