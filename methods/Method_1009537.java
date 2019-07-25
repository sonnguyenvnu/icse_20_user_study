public StateVariable build(){
  return new StateVariable(name,new StateVariableTypeDetails(dataType,defaultValue,allowedValues == null || allowedValues.size() == 0 ? null : allowedValues.toArray(new String[allowedValues.size()]),allowedValueRange == null ? null : new StateVariableAllowedValueRange(allowedValueRange.minimum,allowedValueRange.maximum,allowedValueRange.step)),eventDetails);
}
