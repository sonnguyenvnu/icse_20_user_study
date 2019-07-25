public int size(final String path) throws IOException {
  send("SIZE " + path);
  final String reply=receive();
  if (getStatusCode(reply) != 213) {
    throw new IOException(reply);
  }
  try {
    return Integer.parseInt(reply.substring(4));
  }
 catch (  final NumberFormatException e) {
    throw new IOException(reply);
  }
}
