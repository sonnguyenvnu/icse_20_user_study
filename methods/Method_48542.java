private static StandardKeyInformation getKeyInformation(final ParameterIndexField field){
  return new StandardKeyInformation(field.getFieldKey(),field.getParameters());
}
