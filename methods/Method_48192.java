private <O>StaticBuffer object2StaticBuffer(final O value){
  if (value == null)   throw Graph.Variables.Exceptions.variableValueCanNotBeNull();
  if (!serializer.validDataType(value.getClass()))   throw Graph.Variables.Exceptions.dataTypeOfVariableValueNotSupported(value);
  DataOutput out=serializer.getDataOutput(128);
  out.writeClassAndObject(value);
  return out.getStaticBuffer();
}
