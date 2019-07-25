@Override public void execute(String commandName,BufferedReader reader,BufferedWriter writer) throws Py4JException, IOException {
  String targetObjectId=reader.readLine();
  String methodName=reader.readLine();
  List<Object> arguments=getArguments(reader);
  ReturnObject returnObject=invokeMethod(methodName,targetObjectId,arguments);
  String returnCommand=Protocol.getOutputCommand(returnObject);
  logger.finest("Returning command: " + returnCommand);
  writer.write(returnCommand);
  writer.flush();
}
