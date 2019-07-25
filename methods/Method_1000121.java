public static Value decode(byte[] bytes){
  Operator operator=Operator.valueOf(bytes[0]);
  byte[] value=null;
  if (bytes.length > 1) {
    value=Arrays.copyOfRange(bytes,1,bytes.length);
  }
  return Value.of(operator,value);
}
