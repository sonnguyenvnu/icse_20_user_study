@Override public void execute(String commandName,BufferedReader reader,BufferedWriter writer) throws Py4JException, IOException {
  if (!COMMAND_NAME.equals(commandName)) {
    writer.write(Protocol.getOutputErrorCommand("Authentication error: unexpected command."));
    writer.flush();
    throw new Py4JAuthenticationException(String.format("Expected %s, got %s instead.",COMMAND_NAME,commandName));
  }
  String clientToken=reader.readLine();
  if (authToken.equals(clientToken)) {
    writer.write(Protocol.getOutputVoidCommand());
    writer.flush();
    hasAuthenticated=true;
  }
 else {
    writer.write(Protocol.getOutputErrorCommand("Authentication error: bad auth token received."));
    writer.flush();
    throw new Py4JAuthenticationException("Client authentication unsuccessful.");
  }
}
