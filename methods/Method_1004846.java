public String consume() throws IOException {
  jsonGenerator.flush();
  String s=buffer.toString();
  buffer.reset();
  return s;
}
