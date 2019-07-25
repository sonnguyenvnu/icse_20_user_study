@Override public void execute(String commandName,BufferedReader reader,BufferedWriter writer) throws Py4JException, IOException {
  String returnCommand=null;
  String subCommand=safeReadLine(reader,false);
  if (subCommand.equals(FIELD_GET_SUB_COMMAND_NAME)) {
    returnCommand=getField(reader);
  }
 else   if (subCommand.equals(FIELD_SET_SUB_COMMAND_NAME)) {
    returnCommand=setField(reader);
  }
 else {
    returnCommand=Protocol.getOutputErrorCommand("Unknown Field SubCommand Name: " + subCommand);
  }
  logger.finest("Returning command: " + returnCommand);
  writer.write(returnCommand);
  writer.flush();
}
