private String pwd() throws IOException {
  send("PWD");
  final String reply=receive();
  if (isNotPositiveCompletion(reply)) {
    throw new IOException(reply);
  }
  return reply.substring(5,reply.lastIndexOf('"'));
}
