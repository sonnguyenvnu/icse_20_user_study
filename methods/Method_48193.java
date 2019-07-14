private <O>O staticBuffer2Object(final StaticBuffer s,Class<O> dataType){
  Object value=serializer.readClassAndObject(s.asReadBuffer());
  Preconditions.checkArgument(dataType.isInstance(value),"Could not deserialize to [%s], got: %s",dataType,value);
  return (O)value;
}
