public Object destination(String destPropertyPath){
  if (destPropertyPath == null)   errors.errorNullArgument("destPropertyPath");
  String[] propertyNames=DOT_PATTERN.split(destPropertyPath);
  destinationMutators=new ArrayList<Mutator>(propertyNames.length);
  ValueWriter<?> valueWriter=configuration.valueMutateStore.getFirstSupportedWriter(destinationType);
  if (valueWriter != null)   for (  String propertyName : propertyNames)   destinationMutators.add(ValueWriterPropertyInfo.create(valueWriter,propertyName));
 else {
    Mutator mutator=null;
    for (    String propertyName : propertyNames) {
      Class<?> propertyType=mutator == null ? destinationType : mutator.getType();
      mutator=PropertyInfoRegistry.mutatorFor(propertyType,propertyName,configuration);
      if (mutator == null) {
        errors.errorInvalidDestinationPath(destPropertyPath,propertyType,propertyName);
        return null;
      }
      destinationMutators.add(mutator);
    }
  }
  return null;
}
