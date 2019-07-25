@Override public void execute(String commandName,BufferedReader reader,BufferedWriter writer) throws Py4JException, IOException {
  String returnCommand=null;
  Throwable exception=(Throwable)Protocol.getObject(reader.readLine(),this.gateway);
  reader.readLine();
  String stackTrace=Protocol.getThrowableAsString(exception);
  ReturnObject rObject=ReturnObject.getPrimitiveReturnObject(stackTrace);
  returnCommand=Protocol.getOutputCommand(rObject);
  logger.finest("Returning command: " + returnCommand);
  writer.write(returnCommand);
  writer.flush();
}
