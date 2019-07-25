private String receive() throws IOException {
  String reply;
  while (true) {
    if (this.clientInput == null) {
      throw new IOException("Server has presumably shut down the connection.");
    }
    reply=this.clientInput.readLine();
    if (reply == null) {
      throw new IOException("Server has presumably shut down the connection.");
    }
    Data.logger.info("< " + reply);
    if (reply.length() >= 4 && Character.isDigit(reply.charAt(0)) && Character.isDigit(reply.charAt(1)) && Character.isDigit(reply.charAt(2)) && (reply.charAt(3) == ' ')) {
      break;
    }
  }
  return reply;
}
