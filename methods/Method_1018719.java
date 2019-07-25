@Override public void execute(String commandName,BufferedReader reader,BufferedWriter writer) throws Py4JException, IOException {
  char subCommand=safeReadLine(reader).charAt(0);
  String returnCommand=null;
  if (subCommand == ARRAY_GET_SUB_COMMAND_NAME) {
    returnCommand=getArray(reader);
  }
 else   if (subCommand == ARRAY_SET_SUB_COMMAND_NAME) {
    returnCommand=setArray(reader);
  }
 else   if (subCommand == ARRAY_SLICE_SUB_COMMAND_NAME) {
    returnCommand=sliceArray(reader);
  }
 else   if (subCommand == ARRAY_LEN_SUB_COMMAND_NAME) {
    returnCommand=lenArray(reader);
  }
 else   if (subCommand == ARRAY_CREATE_SUB_COMMAND_NAME) {
    returnCommand=createArray(reader);
  }
 else {
    returnCommand=Protocol.getOutputErrorCommand("Unknown Array SubCommand Name: " + subCommand);
  }
  logger.finest("Returning command: " + returnCommand);
  writer.write(returnCommand);
  writer.flush();
}
