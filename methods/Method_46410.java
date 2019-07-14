public void writeTo(Output output,int number,ListMultimap value,boolean repeated) throws IOException {
  byte[] bytes=javaSerializer.serialize(value);
  output.writeBytes(number,ByteString.copyFrom(bytes),repeated);
}
